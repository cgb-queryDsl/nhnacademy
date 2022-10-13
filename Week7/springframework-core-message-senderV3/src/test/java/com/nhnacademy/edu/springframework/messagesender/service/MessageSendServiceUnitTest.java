package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MessageSendServiceUnitTest {

    @Test
    void test() {
        MessageSender sender = Mockito.mock(MessageSender.class);
        MessageSendService service = new MessageSendService(sender);

        service.doSendMessage();

        Mockito.verify(sender, Mockito.times(1))
                .sendMessage(Mockito.any(User.class), Mockito.any(String.class));
    }

}