package com.nhnacademy.edu.springboot.student.adaptor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nhnacademy.edu.springboot.student.domain.Account;
import com.nhnacademy.edu.springboot.student.domain.AccountService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountBackController {

    private final AccountService accountService;

    public AccountBackController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody AccountRegister accountRegister) {
        log.info("account = {}", accountRegister);
        Account account = new Account(Long.valueOf(accountRegister.getId()), accountRegister.getName(), Integer.parseInt(accountRegister.getBalance()));
        return accountService.createAccount(account);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return "OK";
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class AccountRegister {
        private String id;
        private String name;
        private String balance;
    }
}
