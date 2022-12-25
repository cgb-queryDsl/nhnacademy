package com.nhnacademy.edu.finaltest.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties("init.file")
public class SystemProperties {
    private String name;
}
