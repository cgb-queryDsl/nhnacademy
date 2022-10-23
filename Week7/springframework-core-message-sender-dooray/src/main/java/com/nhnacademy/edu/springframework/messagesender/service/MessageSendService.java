package com.nhnacademy.edu.springframework.messagesender.service;

import com.nhnacademy.edu.springframework.messagesender.User;
import com.nhnacademy.edu.springframework.messagesender.annotation.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class MessageSendService {

//    생성자 주입용
//    private final MessageSender messageSender;

    // Setter 주입용
    private MessageSender messageSender;

    @Value("${name}")
    private String name;

    @Autowired
    @SMS    // bean qualifier 정의된 빈을 주입해주기 때문에 이름을 지정안해도 됨 || String value() 정의해서 이름 지정해도 됨
    public MessageSendService(MessageSender messageSender) {
        System.out.println("----------- Injection -----------");
        this.messageSender = messageSender;
    }

    public MessageSendService() {
    }

    public void setMessageSender(MessageSender messageSender) {
        System.out.println("Setter 주입");
//        System.out.println("Autowired 주입");
        this.messageSender = messageSender;
    }

    public void doSendMessage() {
        System.out.println("From : " + name);
        messageSender.sendMessage(new User("rudtj2966@gmail.com", "01066294869"),
                "Hi, hungry...");
    }
    
}
