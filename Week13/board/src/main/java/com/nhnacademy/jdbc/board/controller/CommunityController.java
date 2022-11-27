package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.domain.Page;
import com.nhnacademy.jdbc.board.domain.dto.PageSizeOffset;
import com.nhnacademy.jdbc.board.domain.dto.PostByCommunity;
import com.nhnacademy.jdbc.board.service.PostService;
import com.nhnacademy.jdbc.board.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class CommunityController {

    private final String SESSION = "session";
    private final int SIZE = 20;
    private final PostService postService;
    private final ReplyService replyService;

    public CommunityController(PostService postService, ReplyService replyService) {
        this.postService = postService;
        this.replyService = replyService;
    }

    @GetMapping(value = "/community")
    public String community(@RequestParam(value = "page", required = false) Integer page, Model model, HttpSession session) {
        Long loginUserId = extractedLoginUserIdFromSession(session);
        String userNickname = extractedNicknameFromSession(session);

        int offset = (page - 1) * SIZE;
        PageSizeOffset pageSizeOffset = new PageSizeOffset(SIZE, offset);

        Page<PostByCommunity> posts = null;

        if (loginUserId == 1) {
            posts = postService.getAllPosts(pageSizeOffset);
            for (PostByCommunity post : posts.getContent()) {
                Long postId = post.getId();
                Long commentCnt = replyService.getReplies(postId);
                post.setCommentCount(commentCnt);
            }
        } else {
            posts = postService.getAllPostsNotDeleted(pageSizeOffset);
            for (PostByCommunity post : posts.getContent()) {
                Long postId = post.getId();
                Long commentCnt = replyService.getRepliesNotDeleted(postId);
                post.setCommentCount(commentCnt);
            }
        }

        long totalCount = posts.getTotalCount();
        List<Integer> pages = new ArrayList<>();

        long tmp = totalCount / SIZE;
        for (int i = 1; i <= tmp+1; i++) {
            pages.add(i);
        }

        model.addAttribute("loginUserId", loginUserId);
        model.addAttribute("userNickname", userNickname);

        model.addAttribute("posts", posts.getContent());
        model.addAttribute("pages", pages);

        return "community/community";
    }

    private String extractedNicknameFromSession(HttpSession session) {
        String temp = (String) session.getAttribute(SESSION);
        if (Objects.nonNull(temp)) {
            String userNickname = temp.split("-")[1];
            return userNickname;
        }

        return null;
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
