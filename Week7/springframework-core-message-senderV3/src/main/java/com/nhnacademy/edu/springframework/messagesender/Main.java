package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.service.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.service.SmsMessageSender;

public class Main {

    public static void main(String[] args) {
//        Main main = new Main();
//        User user = new User("rudtj2966@gmail.com", "010-6629-4869");
//
//        main.sendSmsMessage(user, "hi");

        new MessageSendService(new EmailMessageSender()).doSendMessage();
        new MessageSendService(new SmsMessageSender()).doSendMessage();
    }

//    private void sendSmsMessage(User user, String message) {
//        System.out.println("SMS Message Sent to "  + user.getPhoneNumber()
//                + " : " + message);
//    }
//
//    private void sendEmailMessage(User user, String message) {
//        System.out.println("Email Message Sent to "  + user.getPhoneNumber()
//                + " : " + message);
//    }
}
