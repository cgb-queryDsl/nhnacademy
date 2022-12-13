package com.nhnacademy.edu.springboot.student.adaptor;

import com.nhnacademy.edu.springboot.student.domain.Account;
import com.nhnacademy.edu.springboot.student.domain.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountWebController {

    private final AccountService accountService;

    public AccountWebController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/web/accounts/{id}")
    public String getAccount(@PathVariable("id") Long id, Model model) {
        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);

        return "account";
    }
}
