//package com.nhnacademy.edu.springboot.student.domain;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//class AccountRepositoryTest {
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Test
//    void testAccountRepository() {
//        // given
//        Account account = new Account(1l, "123-1", 3000);
//        accountRepository.save(account);
//
//        // when
//        Optional<Account> actual = accountRepository.findById(1l);
//
//        // then
//        assertThat(actual).isPresent();
//        assertThat(actual.get()).isEqualTo(account);
//    }
//
//}