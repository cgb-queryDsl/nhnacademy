package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.aop.TimeLoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
public class AopConfig {

    @Bean
    public TimeLoggingAspect timeLoggingAspect() {
        return new TimeLoggingAspect();
    }
}
