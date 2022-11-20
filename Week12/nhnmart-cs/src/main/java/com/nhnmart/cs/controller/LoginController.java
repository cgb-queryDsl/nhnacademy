package com.nhnmart.cs.controller;

import com.nhnmart.cs.domain.Customer;
import com.nhnmart.cs.domain.dto.CustomerLoginRequest;
import com.nhnmart.cs.exception.LoginFailException;
import com.nhnmart.cs.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final String SESSION_ID = "sessionId";
    private final CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping
    public String login(@Valid @ModelAttribute CustomerLoginRequest customerLoginRequest, HttpSession session,
                        HttpServletResponse response) throws IOException {
        Customer customer = customerRepository.getCustomer(customerLoginRequest.getId());

        if (isAdmin(customerLoginRequest)) {
            adminLogin(customerLoginRequest, session, response, customer);
            return "adminIndex";
        }

        if (isCorrectIdPassword(customerLoginRequest, customer)) {
            customerLogin(session, response, customer);
            return "customerIndex";
        }

        throw new LoginFailException(customerLoginRequest.getId());
    }

    private void customerLogin(HttpSession session, HttpServletResponse response, Customer customer) throws IOException {
        session.setAttribute(SESSION_ID, customer.getId() + "-on");
        response.sendRedirect("/customer");
    }

    private void adminLogin(CustomerLoginRequest customerLoginRequest, HttpSession session, HttpServletResponse response, Customer customer) throws IOException {
        if (isCorrectIdPassword(customerLoginRequest, customer)) {
            session.setAttribute(SESSION_ID,  "admin-" + customer.getName() + "-on");
            response.sendRedirect("/admin");
        }
    }

    private boolean isCorrectIdPassword(CustomerLoginRequest customerLoginRequest, Customer customer) {
        return customer.getId().equals(customerLoginRequest.getId()) && customer.getPassword().equals(customerLoginRequest.getPassword());
    }

    private boolean isAdmin(CustomerLoginRequest customerLoginRequest) {
        return customerLoginRequest.getId().equals("admin");
    }

    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound(LoginFailException ex) {
        log.error("login fail = {}", ex);
    }
}
