package com.nhnmart.cs.controller;

import com.nhnmart.cs.domain.Customer;
import com.nhnmart.cs.exception.LoginFailException;
import com.nhnmart.cs.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LoginControllerTest {

    MockMvc mockMvc;
    CustomerRepository customerRepository;
    protected MockHttpSession session;
    private static final String SESSION_ID = "sessionId";

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(customerRepository))
                .build();
        session = new MockHttpSession();
    }

    @Test
    void loginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andDo(print());
    }

    @Test
    void adminLogin_success() throws Exception {
        String id = "admin";
        String password = "12345";

        Customer customer = new Customer("admin", "12345", "CS 담당자");
        when(customerRepository.getCustomer("admin")).thenReturn(customer);
        session.setAttribute(SESSION_ID,  "admin" + customer.getName() + "-on");

        mockMvc.perform(post("/login")
                        .param("id", id)
                        .param("password", password)
                        .session(session))
                .andExpect(view().name("adminIndex"))
                .andDo(print());
    }

    @Test
    void customerLogin_success() throws Exception {
        String id = "customer1";
        String password = "123";

        Customer customer = new Customer("customer1", "123", "고객 1");
        when(customerRepository.getCustomer("customer1")).thenReturn(customer);
        session.setAttribute(SESSION_ID, customer.getId() + "-on");

        mockMvc.perform(post("/login")
                        .param("id", id)
                        .param("password", password)
                        .session(session))
                .andExpect(view().name("customerIndex"))
                .andDo(print());
    }

    @Test
    void login_fail() throws Exception {
        String id = "customer1";
        String password = "123aaa";

        Customer customer = new Customer("customer1", "123", "고객 1");
        when(customerRepository.getCustomer("customer1")).thenReturn(customer);

        this.mockMvc.perform(post("/login")
                                .param("id", id)
                                .param("password", password))
                    .andExpect((result) ->
                            assertTrue(result.getResolvedException().getClass().isAssignableFrom(LoginFailException.class)))
                    .andReturn();
    }
}