package com.nhnacademy.edu.springboot.student.domain;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();
    Account getAccount(Long id);
    Account createAccount(Account account);
    void deleteAccount(Long id);
}
