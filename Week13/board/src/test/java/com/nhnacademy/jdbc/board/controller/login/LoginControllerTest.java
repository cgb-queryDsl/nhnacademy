package com.nhnacademy.jdbc.board.controller.login;

import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LoginControllerTest {

    MockMvc mockMvc;
    UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userService))
                .build();

    }

    @Test
    void login_success() throws Exception {
        String loginId = "aaa";
        String loginPw = "123";

        User user = new User(1L, "aaa", "123", "name", new Date());
        Optional<User> optional = Optional.of(user);

        when(userService.getLoginUser(loginId)).thenReturn(optional);

        mockMvc.perform(post("/login")
                        .param("id", loginId)
                        .param("pw", loginPw))
                .andExpect(view().name("redirect:/community?page=1"))
                .andDo(print());
    }

    @Test
    void login_fail_not_match_Id_Pw() throws Exception {
        String loginId = "aaa";
        String loginPw = "123";

        User user = new User(1L, "aaa", "456", "name", new Date());
        Optional<User> optional = Optional.of(user);

        when(userService.getLoginUser(loginId)).thenReturn(optional);

        mockMvc.perform(post("/login")
                        .param("id", loginId)
                        .param("pw", loginPw))
                .andExpect(view().name("index/index"))
                .andDo(print());

    }

    @Test
    void login_fail_noUser_in_DB() throws Exception {
        String loginId = "No data";
        String loginPw = "No data";

        User user = new User(1L, "aaa", "456", "name", new Date());
        Optional<User> optional = Optional.of(user);

        when(userService.getLoginUser(loginId)).thenReturn(optional);

        mockMvc.perform(post("/login")
                        .param("id", loginId)
                        .param("pw", loginPw))
                .andExpect(view().name("index/index"))
                .andDo(print());
    }

}