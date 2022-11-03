package com.nhnacademy.gw1.bank.exception;

public class MoneyIsNegativeException extends RuntimeException{
    public MoneyIsNegativeException(double money) {
        super("Money is negative : " + money);
    }
}
