package com.nhnacademy.gw1.parking.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(long money) {
        super("Don't enough money : " + money);
    }
}
