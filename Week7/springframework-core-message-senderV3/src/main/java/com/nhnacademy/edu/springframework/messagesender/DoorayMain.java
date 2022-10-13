package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.config.DoorayConfig;
import com.nhnacademy.edu.springframework.messagesender.service.DooraySendService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DoorayMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DoorayConfig.class);

        DooraySendService service = context.getBean(DooraySendService.class);
        service.send();
    }
}
