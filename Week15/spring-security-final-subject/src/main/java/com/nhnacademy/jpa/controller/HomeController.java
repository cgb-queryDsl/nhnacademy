package com.nhnacademy.jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Objects;

@Slf4j
@Controller
@PropertySource("classpath:github.properties")
public class HomeController {

    @Value("${client_id}")
    private String clientId;

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (Objects.nonNull(principal)) {
            String name = principal.getName();
            System.out.println("name = " + name);
            model.addAttribute("residentId", name.split("-")[0]);
        }

        model.addAttribute("clientId", clientId);
        return "index";
    }
}
