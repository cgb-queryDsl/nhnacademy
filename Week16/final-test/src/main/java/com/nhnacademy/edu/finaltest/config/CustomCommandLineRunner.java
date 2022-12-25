package com.nhnacademy.edu.finaltest.config;

import com.nhnacademy.edu.finaltest.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesScan
public class CustomCommandLineRunner implements CommandLineRunner {

    private final SystemProperties systemProperties;
    private final InitService initService;

    public CustomCommandLineRunner(SystemProperties systemProperties, InitService initService) {
        this.systemProperties = systemProperties;
        this.initService = initService;
    }

    @Override
    public void run(String... args) throws Exception {
        initService.initialize(systemProperties.getName());
    }
}
