package com.nhnmart.cs.controller;

import com.nhnmart.cs.domain.Category;
import com.nhnmart.cs.domain.Customer;
import com.nhnmart.cs.domain.Post;
import com.nhnmart.cs.exception.PostNotFoundException;
import com.nhnmart.cs.exception.ValidationFailedException;
import com.nhnmart.cs.repository.CustomerRepository;
import com.nhnmart.cs.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    MockMvc mockMvc;
    PostRepository postRepository;
    CustomerRepository customerRepository;
    MockHttpSession session;
    private static final String SESSION_ID = "sessionId";
    private static final String CUSTOMER = "customer1";
    private static final String DIR = "/Users/gyeongseo/Downloads/";
    private static final String FILE = "1.jpeg";
    private static final String CONTENT_TYPE = "image/jpeg";

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        customerRepository = mock(CustomerRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController(postRepository, customerRepository)).build();
        session = new MockHttpSession();
        session.setAttribute(SESSION_ID, CUSTOMER + "-on");
    }

    @Test
    void postForm() throws Exception {
        mockMvc.perform(get("/customer/registerPost"))
                .andExpect(status().isOk())
                .andExpect(view().name("postRegisterForm"))
                .andDo(print());
    }

    @Test
    void customerIndex() throws Exception {
        Customer customer = new Customer("customer1", "123", "고객 1");
        String [] files = {"file"};
        List<Post> postList = Arrays.asList(new Post(customer, "title", Category.COMPLAIN, "content", "time", files));
        when(customerRepository.getCustomer("customer1")).thenReturn(customer);
        when(postRepository.getPostListByCustomer(customer.getId())).thenReturn(postList);

        mockMvc.perform(get("/customer")
                        .session(session))
                .andExpect(model().attribute("postList", postList))
                .andExpect(model().attribute("customerName", customer.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("customerIndex"))
                .andDo(print());
    }

    @Test
    void postRegister_validation() {
        Throwable th = catchThrowable(() ->
                mockMvc.perform(post("/customer")
                                .param("title", "")
                                .param("category", "")
                                .param("file", "")
                                .param("content", ""))
                        .andDo(print()));

        assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }

    @Test
    void postRegister() throws Exception {
        Customer customer = new Customer("customer1", "123", "고객 1");
        when(customerRepository.getCustomer("customer1")).thenReturn(customer);
        FileInputStream fileInputStream = new FileInputStream(DIR + FILE);
        MockMultipartFile file1 = new MockMultipartFile("file", FILE, CONTENT_TYPE, fileInputStream);

        mockMvc.perform(multipart("/customer")
                        .file(file1)
                        .session(session)
                        .param("title", "title")
                        .param("category", "COMPLAIN")
                        .param("content", "content"))
                .andExpect(status().isFound())
                .andDo(print());
    }

    @Test
    void viewPost_notFound_post() {
        Throwable th = catchThrowable(() ->
                mockMvc.perform(get("/customer/postList/{postId}", 1))
                        .andDo(print()));

        assertThat(th).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(PostNotFoundException.class);
    }

    @Test
    void viewPost_success() throws Exception {
        Customer customer = new Customer("customer1", "123", "고객 1");
        String [] files = {"file"};
        Post post = new Post(customer, "title", Category.OTHERS, "content", "time", files);

        when(postRepository.getPost(1)).thenReturn(post);

        mockMvc.perform(get("/customer/postList/{postId}", 1)
                        .session(session))
                .andExpect(model().attribute("post", post))
                .andExpect(model().attribute("files", files))
                .andDo(print());
    }

    @Test
    void viewPost_fail_not_match_customerId() throws Exception {
        Customer customer = new Customer("customer2", "123", "고객 1");
        String [] files = {"file"};
        Post post = new Post(customer, "title", Category.OTHERS, "content", "time", files);

        when(postRepository.getPost(1)).thenReturn(post);

        mockMvc.perform(get("/customer/postList/{postId}", 1)
                        .session(session))
                .andExpect(view().name("noAuthorized"))
                .andDo(print());
    }

}
