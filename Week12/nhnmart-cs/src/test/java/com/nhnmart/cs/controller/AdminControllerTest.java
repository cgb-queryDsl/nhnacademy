package com.nhnmart.cs.controller;

import com.nhnmart.cs.domain.Category;
import com.nhnmart.cs.domain.Customer;
import com.nhnmart.cs.domain.Post;
import com.nhnmart.cs.exception.PostNotFoundException;
import com.nhnmart.cs.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.net.BindException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminControllerTest {

    MockMvc mockMvc;
    PostRepository postRepository;
    MockHttpSession session;
    private static final String SESSION_ID = "sessionId";
    private static final String ADMIN = "admin";
    private static final String ADMIN_NAME = "CS 담당자";

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new AdminController(postRepository)).build();
        session = new MockHttpSession();
        session.setAttribute(SESSION_ID, ADMIN + "-" + ADMIN_NAME + "-on");
    }

    @Test
    void adminIndex() throws Exception {
        Customer customer = new Customer("customer1", "123", "고객 1");
        String files [] = {"file1"};
        Post post = new Post(customer, "title", Category.OTHERS, "content", "writeTime", files);
        List<Post> postListYetAnswer = Arrays.asList(post);
        when(postRepository.getPostListYetAnswer()).thenReturn(postListYetAnswer);

        mockMvc.perform(get("/admin")
                    .session(session))
                .andExpect(model().attribute("postListYetAnswer", postListYetAnswer))
                .andExpect(status().isOk())
                .andExpect(view().name("adminIndex"))
                .andDo(print());
    }

    @Test
    void adminIndexByCategory() throws Exception {
        Customer customer = new Customer("customer1", "123", "고객 1");
        String files [] = {"file1"};
        Post post1 = new Post(customer, "title", Category.OTHERS, "content", "writeTime", files);
        Post post2 = new Post(customer, "title", Category.OTHERS, "content", "writeTime", files);
        List<Post> postListByCategory = Arrays.asList(post1, post2);
        when(postRepository.getPostListByCategory(Category.OTHERS))
                .thenReturn(postListByCategory);

        mockMvc.perform(get("/admin/search")
                        .queryParam("category", String.valueOf(Category.COMPLAIN)))
                .andExpect(status().isOk())
                .andExpect(view().name("adminSearchByCategory"))
                .andDo(print());
    }

    @Test
    void adminAnswerRegisterForm_notFound_post() {
        Throwable th = catchThrowable(() ->
                mockMvc.perform(get("/admin/postList/{postId}", 1))
                        .andDo(print()));

        assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(PostNotFoundException.class);
    }

    @Test
    void adminAnswerRegisterForm() throws Exception {
        Customer customer = new Customer("customer1", "123", "고객 1");
        String files [] = {"file1"};
        Post post = new Post(customer, "title", Category.OTHERS, "content", "writeTime", files);

        when(postRepository.getPost(1)).thenReturn(post);

        mockMvc.perform(get("/admin/postList/{postId}", 1))
                .andExpect(model().attribute("post", post))
                .andExpect(model().attribute("files", files))
                .andExpect(view().name("adminAnswerForm"))
                .andDo(print());
    }

    @Test
    void adminAnswerRegister_validation() throws Exception {
        Customer customer = new Customer("customer1", "123", "고객 1");
        String files [] = {"file1"};
        Post post = new Post(customer, "title", Category.OTHERS, "content", "writeTime", files);
        when(postRepository.getPost(1)).thenReturn(post);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 50000; i++) {
            sb.append("a");
        }

        this.mockMvc.perform(post("/admin/postList/{postId}/answerRegister", 1)
                            .param("answerContent", sb.toString()))
                    .andExpect((result) -> assertThat(result.getResolvedException().getClass().isAssignableFrom(BindException.class)))
                    .andDo(print())
                    .andReturn();
    }

    @Test
    void adminAnswerRegisterComplete() throws Exception {
        Customer customer = new Customer("customer1", "123", "고객 1");
        String files [] = {"file1"};
        Post post = new Post(customer, "title", Category.OTHERS, "content", "writeTime", files);
        when(postRepository.getPost(1)).thenReturn(post);

        mockMvc.perform(post("/admin/postList/{postId}/answerRegister", 1)
                    .param("answerContent", "answer complete")
                    .session(session))
                .andExpect(view().name("redirect:/admin"))
                .andExpect(status().isFound())
                .andDo(print());
    }
}