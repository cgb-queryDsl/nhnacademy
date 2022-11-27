package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.domain.dto.InsertReply;
import com.nhnacademy.jdbc.board.domain.dto.UpdateReply;
import com.nhnacademy.jdbc.board.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/post")
public class ReplyController {

    private static final String SESSION = "session";
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/{postId}/reply")
    public String replyRegister(@PathVariable("postId") Long postId, @RequestParam("comment") String comment,
                        Model model, HttpSession session) {
        String validationError = processCommentValidation(postId, comment, model);

        if (validationError != null) {
            log.error("Reply Register Validation Error = {}", validationError);
            return validationError;
        }

        Long userId = extractedUserIdFromSession(session);
        InsertReply insertReply = new InsertReply(postId, userId, comment);
        replyService.registerReply(insertReply);

        return "redirect:/post/" + postId;
    }

    @GetMapping("/{postId}/reply/update/{replyId}")
    public String replyUpdateForm(@PathVariable("postId") Long postId,
                                  @PathVariable("replyId") Long replyId, Model model) {
        String reply = replyService.getReply(replyId);

        model.addAttribute("postId", postId);
        model.addAttribute("replyId", replyId);
        model.addAttribute("reply", reply);
        return "reply/replyUpdateForm";
    }


    @PostMapping("/{postId}/reply/update/{replyId}")
    public String replyUpdate(@PathVariable("postId") Long postId,
                              @PathVariable("replyId") Long replyId, @RequestParam("comment") String comment,
                              Model model, HttpSession session) {
        String validationError = processCommentValidation(postId, comment, model);
        if (validationError != null) {
            log.error("Reply Update Validation Error = {}", validationError);
            return validationError;
        }
        Long userId = extractedUserIdFromSession(session);
        UpdateReply updateReply = new UpdateReply(userId, replyId, comment);
        replyService.modifyReply(updateReply);

        return "redirect:/post/" + postId;
    }

    @PostMapping("/{postId}/reply/delete/{replyId}")
    public String replyDeleted(@PathVariable("postId") Long postId, @PathVariable("replyId") Long replyId) {
        replyService.deleteReply(replyId);
        return "redirect:/post/" + postId;
    }

    @PostMapping("/{postId}/reply/restore/{replyId}")
    public String replyRestore(@PathVariable("postId") Long postId, @PathVariable("replyId") Long replyId) {
        replyService.restoreReply(replyId);
        return "redirect:/post/" + postId;
    }

    private Long extractedUserIdFromSession(HttpSession session) {
        String temp = (String) session.getAttribute(SESSION);
        Long userId = Long.valueOf(temp.split("-")[0]);
        return userId;
    }

    private String processCommentValidation(Long postId, String comment, Model model) {
        if (comment.length() == 0) {
            model.addAttribute("postId", postId);
            model.addAttribute("message", "빈 댓글 등록은 불가능합니다.");
            return "validation/replyValidationError";
        } else if (comment.length() > 1000) {
            model.addAttribute("postId", postId);
            model.addAttribute("message", "댓글은 1,000자를 넘을 수 없습니다.");
            return "validation/replyValidationError";
        }
        return null;
    }
}
