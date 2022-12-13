package com.nhnacademy.edu.studentopen.controller;

import com.nhnacademy.edu.studentopen.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class AccountOpenController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${backEnd.address}")
    String backEndAddress;

    @GetMapping("/accounts")
    public String getAccounts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity response = restTemplate.exchange(backEndAddress + "/accounts", HttpMethod.GET, entity, String.class);

        log.info("getAccounts response = {}", response.getBody());

        return (String) response.getBody();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity<Account> response = restTemplate.exchange(backEndAddress + "/accounts/" + id, HttpMethod.GET, entity, Account.class);

        log.info("getAccount response = {}", response.getBody());

        return response.getBody();
    }

    @PostMapping("/accounts")
    public Account registerAccount(@RequestBody Account account) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(account.getId()));
        params.put("name", account.getNumber());
        params.put("balance", String.valueOf(account.getBalance()));

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<Account> response = restTemplate.exchange(backEndAddress + "/accounts", HttpMethod.POST, entity, Account.class);

        log.info("registerAccount response = {}", response.getBody());

        return response.getBody();
    }

    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity response = restTemplate.exchange(backEndAddress + "/accounts/" + id, HttpMethod.DELETE, entity, String.class);

        log.info("getAccount response = {}", response.getBody());

        return response.getBody().equals("OK") ? "OK" : "No Ok";
    }
}
