package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.domain.dto.*;
import com.nhnacademy.jdbc.board.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

    private static final String SESSION = "session";
    private static final String UPLOAD_DIR = "/Users/gyeongseo/temp/";
    private static final String FILE_UUID_SEPARATOR = "----";
    private final UserService userService;
    private final PostService postService;
    private final FileService fileService;
    private final UuidService uuidService;
    private final ReplyService replyService;

    public PostController(UserService userService, PostService postService, FileService fileService,
                          UuidService uuidService, ReplyService replyService) {
        this.userService = userService;
        this.postService = postService;
        this.fileService = fileService;
        this.uuidService = uuidService;
        this.replyService = replyService;
    }

    @GetMapping("/register")
    public String postRegisterForm(Model model, HttpSession session) {
        Long userId = extractedLoginUserIdFromSession(session);
        Optional<User> optional = userService.getUser(userId);

        model.addAttribute("userNickname", optional.get().getNickname());

        return "post/postRegisterForm";
    }

    @Transactional
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("postRegisterForm") PostRegisterForm postRegisterForm,
                               BindingResult bindingResult, Model model, HttpSession session) throws IOException {
        processRegisterValidTitle(postRegisterForm, bindingResult);
        processRegisterValidContent(postRegisterForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error("Post Register Validation Error = {}", bindingResult);
            extractedErrorMessage(bindingResult, model);
            return "validation/postValidationError";
        }

        Long userId = extractedLoginUserIdFromSession(session);
        postService.registerPost(new InsertPost(postRegisterForm.getTitle(), postRegisterForm.getContent(), userId));

        if (hasInputFiles(postRegisterForm)) {
            List<String> fileNamesAndUuids = extractedFileNameAndUuid(postRegisterForm);
            List<String> originFileNames = new ArrayList<>();
            List<String> uuidFileNames = new ArrayList<>();

            extractedFileName(fileNamesAndUuids, originFileNames);
            extractedUUID(fileNamesAndUuids, uuidFileNames);

            registerFileAndUuid(originFileNames, uuidFileNames);
        }

        return "redirect:/community?page=1";
    }

    @GetMapping("/{postId}")
    public String viewPost(@PathVariable("postId") Long postId, Model model, HttpSession session) {
        ViewPost post = postService.getViewPostData(postId);
        List<FileNameAndUuid> files = fileService.getFiles(postId);
        Long loginUserId = extractedLoginUserIdFromSession(session);
        List<ReplyByPost> replies = null;

        model.addAttribute("post", post);
        model.addAttribute("files", files);

        if (loginUserId == 1) {
            replies = replyService.getAllReplies(postId);
        } else {
            replies = replyService.getAllRepliesNotDeleted(postId);
        }
        model.addAttribute("replies", replies);
        model.addAttribute("loginId", loginUserId);

        return "post/viewPost";
    }

    @GetMapping("/update/{postId}")
    public String updatePostForm(@PathVariable("postId") Long postId, Model model) {
        ViewPost post = postService.getViewPostData(postId);
        List<FileNameAndUuid> files = fileService.getFiles(postId);
        model.addAttribute("post", post);
        model.addAttribute("files", files);

        return "post/postUpdateForm";
    }

    @PostMapping("/update")
    public String updatePost(@ModelAttribute PostUpdateForm postUpdateForm, BindingResult bindingResult,
                             Model model, HttpSession session) {
        processUpdateValidTitle(postUpdateForm, bindingResult);
        processUpdateValidContent(postUpdateForm, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error("Update Post Validation Error = {}", bindingResult);
            extractedErrorMessage(bindingResult, model);
            return "validation/postValidationError";
        }

        Long userId = extractedLoginUserIdFromSession(session);
        postUpdateForm.setUserId(userId);

        postService.modifyPost(postUpdateForm);

        return "redirect:/community?page=1";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/community?page=1";
    }

    @GetMapping("/restore/{postId}")
    public String restorePost(@PathVariable("postId") Long postId) {
        postService.restorePost(postId);
        return "redirect:/community?page=1";
    }

    private void registerFileAndUuid(List<String> originFileNames, List<String> uuidFileNames) {
        int lastPostId = postService.getLastInsertPostId();

        for (int i = 0; i < originFileNames.size(); i++) {
            fileService.registerFile(Long.valueOf(lastPostId), originFileNames.get(i));
            int lastFileId = fileService.lastInsertFileId();
            uuidService.registerUuid(Long.valueOf(lastFileId), Long.valueOf(lastPostId), uuidFileNames.get(i));
        }
    }

    private void extractedFileName(List<String> fileNamesAndUuids, List<String> originFileNames) {
        for (String str : fileNamesAndUuids) {
            String uuid = str.split(FILE_UUID_SEPARATOR)[0];

            originFileNames.add(uuid);
        }
    }

    private void extractedUUID(List<String> fileNamesAndUuids, List<String> uuidFileNames) {
        for (String str : fileNamesAndUuids) {
            String uuid = str.split(FILE_UUID_SEPARATOR)[1];

            uuidFileNames.add(uuid);
        }
    }

    private List<String> extractedFileNameAndUuid(PostRegisterForm postRegisterForm) throws IOException {
        List<String> tmp = new ArrayList<>();

        if (hasInputFiles(postRegisterForm)) {
            for (MultipartFile multipartFile : postRegisterForm.getFiles()) {
                String filename = multipartFile.getOriginalFilename();
                String extension = filename.substring(filename.lastIndexOf("."));
                String storeName = UUID.randomUUID() + extension;

                multipartFile.transferTo(Paths.get(UPLOAD_DIR + storeName));
                tmp.add(multipartFile.getOriginalFilename() + FILE_UUID_SEPARATOR + storeName);
            }
        }

        return tmp;
    }

    private boolean hasInputFiles(PostRegisterForm postRegisterForm) {
        return postRegisterForm.getFiles().get(0).getSize() != 0;
    }

    private void extractedErrorMessage(BindingResult bindingResult, Model model) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<PostValidation> errors = new ArrayList<>();
        for (ObjectError allError : allErrors) {
            errors.add(new PostValidation(allError.getObjectName(), allError.getDefaultMessage()));
        }
        model.addAttribute("errors", errors);
    }

    private void processUpdateValidContent(PostUpdateForm postUpdateForm, BindingResult bindingResult) {
        if (postUpdateForm.getContent().length() == 0) {
            bindingResult.addError(new FieldError("본문", "content", postUpdateForm.getContent(),
                    false, null, null, "본문 내용은 필수입니다."));
        } else if (postUpdateForm.getContent().length() > 20000) {
            bindingResult.addError(new FieldError("본문", "content", postUpdateForm.getContent(),
                    false, null, null, "본문 내용은 20,000자를 넘을 수 없습니다."));
        }
    }

    private void processUpdateValidTitle(PostUpdateForm postUpdateForm, BindingResult bindingResult) {
        if (postUpdateForm.getTitle().length() == 0) {
            bindingResult.addError(new FieldError("제목", "title", postUpdateForm.getTitle(),
                    false, null, null, "제목은 필수입니다."));
        } else if (postUpdateForm.getTitle().length() > 100) {
            bindingResult.addError(new FieldError("제목", "title", postUpdateForm.getTitle(),
                    false, null, null, "제목은 100자를 넘을 수 없습니다."));
        }
    }

    private void processRegisterValidContent(PostRegisterForm postRegisterForm, BindingResult bindingResult) {
        if (postRegisterForm.getContent().length() == 0) {
            bindingResult.addError(new FieldError("본문", "content", postRegisterForm.getContent(),
                    false, null, null, "본문 내용은 필수입니다."));
        } else if (postRegisterForm.getContent().length() > 20000) {
            bindingResult.addError(new FieldError("본문", "content", postRegisterForm.getContent(),
                    false, null, null, "본문 내용은 20,000자를 넘을 수 없습니다."));
        }
    }

    private void processRegisterValidTitle(PostRegisterForm postRegisterForm, BindingResult bindingResult) {
        if (postRegisterForm.getTitle().length() == 0) {
            bindingResult.addError(new FieldError("제목", "title", postRegisterForm.getTitle(),
                    false, null, null, "제목은 필수입니다."));
        } else if (postRegisterForm.getTitle().length() > 100) {
            bindingResult.addError(new FieldError("제목", "title", postRegisterForm.getTitle(),
                    false, null, null, "제목은 100자를 넘을 수 없습니다."));
        }
    }

    private Long extractedLoginUserIdFromSession(HttpSession session) {
        String temp = (String) session.getAttribute(SESSION);
        if (Objects.nonNull(temp)) {
            Long userId = Long.valueOf(temp.split("-")[0]);
            return userId;
        }

        return -9999l;
    }
}
