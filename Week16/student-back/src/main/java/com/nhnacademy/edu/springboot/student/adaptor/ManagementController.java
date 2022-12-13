package com.nhnacademy.edu.springboot.student.adaptor;

import com.nhnacademy.edu.springboot.student.configure.endpoint.CustomHealthIndicator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {

    private final CustomHealthIndicator customHealthIndicator;

    public ManagementController(CustomHealthIndicator customHealthIndicator) {
        this.customHealthIndicator = customHealthIndicator;
    }

    @PostMapping("/management/fail")
    public String fail() {
        customHealthIndicator.setUp(false);
        return "OK";
    }

    @PostMapping("/management/success")
    public String success() {
        customHealthIndicator.setUp(true);
        return "OK";
    }
}
