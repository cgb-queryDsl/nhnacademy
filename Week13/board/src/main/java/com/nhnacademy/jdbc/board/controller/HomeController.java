package com.nhnacademy.jdbc.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
public class HomeController {

    @GetMapping(value = {"/","/index.nhn"})
    public String index(){
        return "index/index";
    }

    @GetMapping("/nonAccess")
    public String noAccess() {
        return "noAccess";
    }
}
