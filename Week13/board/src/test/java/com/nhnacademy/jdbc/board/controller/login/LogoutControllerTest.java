package com.nhnacademy.jdbc.board.controller.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LogoutControllerTest {

    MockMvc mockMvc;
    MockHttpSession session;
    private final String SESSION = "session";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(LogoutController.class)
                .build();
        session = new MockHttpSession();
    }

    @Test
    void logout() throws Exception {
        session.setAttribute(SESSION, "user-on");

        mockMvc.perform(post("/logout")
                        .session(session))
                .andExpect(view().name("index/index"))
                .andDo(print());
    }
}