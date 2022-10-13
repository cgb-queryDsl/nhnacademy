package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.service.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSender;
import com.nhnacademy.edu.springframework.messagesender.service.SmsMessageSender;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
//@ImportResource("classpath:/beans.xml")
public class MainConfig {

    @Bean
    public MessageSender emailMessageSender() {
        return new EmailMessageSender();
    }

    @Bean
    public MessageSender smsMessageSender() {
        return new SmsMessageSender();
    }
}

