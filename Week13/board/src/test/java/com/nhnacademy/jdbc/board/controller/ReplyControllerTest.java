package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.domain.dto.InsertReply;
import com.nhnacademy.jdbc.board.service.ReplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class ReplyControllerTest {

    ReplyService replyService;
    MockMvc mockMvc;
    MockHttpSession session;
    private static final String SESSION = "session";

    @BeforeEach
    void setUp() {
        replyService = mock(ReplyService.class);
        session = new MockHttpSession();
        mockMvc = MockMvcBuilders.standaloneSetup(new ReplyController(replyService))
                .build();
    }

    @Test
    void replyRegister_success() throws Exception {
        session.setAttribute(SESSION, "2-user-on");

        mockMvc.perform(post("/post/{postId}/reply", 1)
                        .session(session)
                        .param("comment", "댓글"))
                .andExpect(view().name("redirect:/post/1"))
                .andDo(print());
    }

    @Test
    void replyRegister_validation() throws Exception {
        mockMvc.perform(post("/post/{postId}/reply", 1)
                        .param("comment", ""))
                .andExpect(view().name("validation/replyValidationError"))
                .andDo(print());
    }

    @Test
    void replyUpdateForm() throws Exception {
        String reply = "abc";

        when(replyService.getReply(1l)).thenReturn(reply);

        mockMvc.perform(get("/post/{postId}/reply/update/{replyId}", 1, 1))
                .andExpect(model().attribute("postId", 1l))
                .andExpect(model().attribute("replyId", 1l))
                .andExpect(model().attribute("reply", reply))
                .andExpect(view().name("reply/replyUpdateForm"))
                .andDo(print());
    }

    @Test
    void replyUpdate() throws Exception {
        session.setAttribute(SESSION, "2-user-on");
        String comment = "modify comment";

        mockMvc.perform(post("/post/{postId}/reply/update/{replyId}", 1, 1)
                            .session(session)
                            .param("comment", comment))
                .andExpect(view().name("redirect:/post/1"))
                .andDo(print());
    }

    @Test
    void replyUpdate_validation() throws Exception {
        mockMvc.perform(post("/post/{postId}/reply", 1)
                        .param("comment", ""))
                .andExpect(view().name("validation/replyValidationError"))
                .andDo(print());
    }

    @Test
    void replyDeleted() throws Exception {
        mockMvc.perform(post("/post/{postId}/reply/delete/{replyId}", 1, 1))
                .andExpect(view().name("redirect:/post/1"))
                .andDo(print());
    }

    @Test
    void replyRestore() throws Exception {
        mockMvc.perform(post("/post/{postId}/reply/restore/{replyId}", 1, 1))
                .andExpect(view().name("redirect:/post/1"))
                .andDo(print());
    }
}