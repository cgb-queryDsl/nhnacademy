package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.service.MessageSendService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiredInjectionMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/beans.xml");

        MessageSendService service = context.getBean("messageSendService", MessageSendService.class);
        service.doSendMessage();
    }
}
