package com.nhnacademy.payment.alert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmsNotice implements PaymentAlert {
    public String sendMessage(boolean isNetwork) {
        if (!isNetwork) {
            return "failed send message";
        }
        return "success send message";
    }
}
