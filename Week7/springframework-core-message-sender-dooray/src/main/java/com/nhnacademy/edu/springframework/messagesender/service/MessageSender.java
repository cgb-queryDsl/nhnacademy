package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;

public interface MessageSender {

    boolean sendMessage(User user, String message);
}
