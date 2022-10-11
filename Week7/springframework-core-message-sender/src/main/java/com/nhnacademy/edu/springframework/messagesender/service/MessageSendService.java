package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;

public class MessageSendService {

//    생성자 주입용
//    private final MessageSender smsMessageSender;

    // Setter 주입용
    private MessageSender smsMessageSender;

    public MessageSendService(MessageSender smsMessageSender) {
        this.smsMessageSender = smsMessageSender;
    }

    public MessageSendService() {
    }

    public void setSmsMessageSender(MessageSender smsMessageSender) {
        System.out.println("Setter 주입");
//        System.out.println("Autowired 주입");
        this.smsMessageSender = smsMessageSender;
    }

    public void doSendMessage() {
        smsMessageSender.sendMessage(new User("rudtj2966@gmail.com", "01066294869"),
                "Hi, hungry...");
    }
}
