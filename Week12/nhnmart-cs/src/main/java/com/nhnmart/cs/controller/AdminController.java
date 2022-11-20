package com.nhnmart.cs.controller;

import com.nhnmart.cs.domain.Category;
import com.nhnmart.cs.domain.Post;
import com.nhnmart.cs.domain.dto.AdminAnswerRequest;
import com.nhnmart.cs.exception.PostNotFoundException;
import com.nhnmart.cs.exception.ValidationFailedException;
import com.nhnmart.cs.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String SESSION_ID = "sessionId";
    private final PostRepository postRepository;

    public AdminController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String adminIndex(Model model) {
        List<Post> postListYetAnswer = postRepository.getPostListYetAnswer();
        model.addAttribute("postListYetAnswer", postListYetAnswer);

        return "adminIndex";
    }

    @GetMapping(value = "/search", params = "category")
    public String adminIndexByCategory(@RequestParam(value = "category", required = false) String searchCategory, Model model) {
        Category category = Category.valueOf(searchCategory);
        List<Post> postListByCategory = postRepository.getPostListByCategory(category);

        model.addAttribute("category", category);
        model.addAttribute("postListByCategory", postListByCategory);

        return "adminSearchByCategory";
    }

    @GetMapping("/postList/{postId}")
    public String adminAnswerRegisterForm(@PathVariable("postId") Long postId, Model model) {
        if (Objects.isNull(postRepository.getPost(postId))) {
            throw new PostNotFoundException(postId);
        }

        Post post = postRepository.getPost(postId);
        String[] files = post.getFiles();

        model.addAttribute("post", post);
        model.addAttribute("files", files);

        return "adminAnswerForm";
    }

    @PostMapping("/postList/{postId}/answerRegister")
    public String adminAnswerRegisterComplete(@Valid @ModelAttribute AdminAnswerRequest adminAnswerRequest,
                                              @PathVariable("postId") Long postId,
                                              BindingResult bindingResult,
                                              HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String answerTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String admin = extractedAdmin(request);

        Post post = postRepository.getPost(postId);

        post.updateAnswer(adminAnswerRequest.getAnswerContent(), answerTime, admin);
        post.answerComplete();

        return "redirect:/admin";
    }

    private String extractedAdmin(HttpServletRequest request) {
        String sessionId = (String) request.getSession().getAttribute(SESSION_ID);
        String admin = sessionId.split("-")[1];
        return admin;
    }
}
