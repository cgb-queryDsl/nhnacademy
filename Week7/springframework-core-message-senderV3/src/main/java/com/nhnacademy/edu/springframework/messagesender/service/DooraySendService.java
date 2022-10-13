package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;
import org.springframework.beans.factory.annotation.Autowired;

public class DooraySendService {

    @Autowired
    private DoorayMessageSender doorayMessageSender;

    public void send() {
        doorayMessageSender.sendMessage(new User("temp", "temp"), "temp");
    }
}
