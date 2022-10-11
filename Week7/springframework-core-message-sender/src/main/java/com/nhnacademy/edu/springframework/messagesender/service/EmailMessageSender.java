package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;

public class EmailMessageSender implements MessageSender {

    public EmailMessageSender() {
        System.out.println("+ EmailMessageSender create");
    }

    public void init() {
        System.out.println("init method called in EmailMessageSender");
    }

    public void destroy() {
        System.out.println("- destroy EmailMessageSender");
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("Email Message Sent to "  + user.getPhoneNumber()
                + " : " + message);
    }
}
