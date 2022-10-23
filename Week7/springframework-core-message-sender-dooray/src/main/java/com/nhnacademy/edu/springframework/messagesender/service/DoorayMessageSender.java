package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.messagesender.User;
import org.springframework.web.client.RestTemplate;

public class DoorayMessageSender implements MessageSender{

    @Override
    public boolean sendMessage(User user, String message) {
        new DoorayHookSender(new RestTemplate(), "https://hook.dooray.com/services/3036349505739914786/3371740733259172017/cdnzcggTTWmx2GtusEMUJw")
                .send(DoorayHook.builder()
                        .botName("박경서")
                        .text("감사합니다.")
                        .build());
        return true;
    }
}
