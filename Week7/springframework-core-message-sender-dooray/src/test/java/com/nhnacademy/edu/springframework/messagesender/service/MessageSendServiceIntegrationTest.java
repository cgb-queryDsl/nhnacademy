package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.config.AopConfig;
import com.nhnacademy.edu.springframework.messagesender.config.MainConfig;
import com.nhnacademy.edu.springframework.messagesender.config.ServiceConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = { MainConfig.class, ServiceConfig.class, AopConfig.class })
public class MessageSendServiceIntegrationTest {

    @Test
    public void test() {
        MessageSender sender = Mockito.mock(MessageSender.class);
        MessageSendService service = new MessageSendService(sender);

        service.doSendMessage();
    }
}
