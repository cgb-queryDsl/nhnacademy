package com.nhnacademy.edu.springboot.student.adaptor;

import com.nhnacademy.edu.springboot.student.config.SystemAuthorProperties;
import com.nhnacademy.edu.springboot.student.config.SystemVersionProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

//    @Value("${system.author.name}")
//    public String author;

    private final SystemAuthorProperties systemAuthorProperties;
    private final SystemVersionProperties systemVersionProperties;

    public SystemController(SystemAuthorProperties systemAuthorProperties, SystemVersionProperties systemVersionProperties) {
        this.systemAuthorProperties = systemAuthorProperties;
        this.systemVersionProperties = systemVersionProperties;
    }

    @GetMapping("/system/author")
    public String getAuthor() {
        return systemAuthorProperties.getName();
    }

    @GetMapping("/system/version")
    public String getVersion() {
        return "{\"version\": \"" + systemVersionProperties.getVersion() +"\"}";
    }

}
