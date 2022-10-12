package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;

public class SmsMessageSender implements MessageSender{

    public SmsMessageSender() {
        System.out.println("+ SmsMessageSender create");
    }

    public void init() {
        System.out.println("init method called in SmsMessageSender");
    }

    public void destroy() {
        System.out.println("- destroy SmsMessageSender");
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to "  + user.getPhoneNumber()
                + " : " + message);
    }
}
