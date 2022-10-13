package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.config.MainConfig;
import com.nhnacademy.edu.springframework.messagesender.config.ServiceConfig;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.service.MessageSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigMain {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context
//                = new AnnotationConfigApplicationContext(MainConfig.class);
//
//        MessageSender emailMessageSender = context.getBean("emailMessageSender", MessageSender.class);
//        MessageSender smsMessageSender = context.getBean("smsMessageSender", MessageSender.class);
//
////        이런 방법도 있다.~~
////        MessageSender temp = MessageSender.class.cast(context.getBean("smsMessageSender"));
//
//        User user = new User("Gs", "01066");
//        emailMessageSender.sendMessage(user, "Hello");
//        smsMessageSender.sendMessage(user, "Hello");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MainConfig.class, ServiceConfig.class);

        MessageSendService service = context.getBean("messageSendService", MessageSendService.class);
        service.doSendMessage();
    }
}
