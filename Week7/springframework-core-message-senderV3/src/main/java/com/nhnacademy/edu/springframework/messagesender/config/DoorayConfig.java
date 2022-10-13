package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.service.DoorayMessageSender;
import com.nhnacademy.edu.springframework.messagesender.service.DooraySendService;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoorayConfig {

    @Bean
    public MessageSender doorayMessageSender() {
        return new DoorayMessageSender();
    }

    @Bean
    public DooraySendService dooraySendService() {
        return new DooraySendService();
    }
}
