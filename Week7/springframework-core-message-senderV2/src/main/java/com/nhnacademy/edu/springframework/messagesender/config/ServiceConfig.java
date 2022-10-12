package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:sender.properties")
public class ServiceConfig {

    @Autowired
    private MainConfig mainConfig;

    @Bean
    public MessageSendService messageSendService() {
        return new MessageSendService(mainConfig.smsMessageSender());
    }
}
