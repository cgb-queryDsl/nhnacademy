package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.service.MessageSender;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/beans.xml");

//        MessageSender sender = context.getBean("emailMessageSender", MessageSender.class);
//
        User user = new User("rudtj2966@gmail.com", "01066294869");
//        sender.sendMessage(user, "hi");

        System.out.println("=================");
//        context.getBean("emailMessageSender", MessageSender.class).sendMessage(user, "hi");
//        context.getBean("emailMessageSender", MessageSender.class).sendMessage(user, "hi");

        System.out.println("=================");
        context.getBean("smsMessageSender", MessageSender.class).sendMessage(user, "hi");
        context.getBean("smsMessageSender", MessageSender.class).sendMessage(user, "hi");

        System.out.println("=================");
        context.close();
    }
}
