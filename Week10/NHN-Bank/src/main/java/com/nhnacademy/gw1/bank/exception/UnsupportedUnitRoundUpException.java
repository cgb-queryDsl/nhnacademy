package com.nhnacademy.gw1.bank.exception;

import com.nhnacademy.gw1.bank.domain.Money;

public class UnsupportedUnitRoundUpException extends RuntimeException{
    public UnsupportedUnitRoundUpException(Money money) {
        super("Unsupported monetary unit : " + money.getUnit());
    }
}
