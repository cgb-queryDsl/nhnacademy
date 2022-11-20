package com.nhnmart.cs.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LogoutControllerTest {

    MockMvc mockMvc;
    protected MockHttpSession session;
    private static final String SESSION_ID = "sessionId";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(LogoutController.class)
                .build();
        session = new MockHttpSession();
    }

    @Test
    void logout() throws Exception {
        session.setAttribute(SESSION_ID, "login-on");

        mockMvc.perform(get("/logout")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

    }

}