package com.nhnacademy.bank;

import java.math.BigDecimal;

/**
 * Atm class.
 */
public class Atm {

    public boolean deposit(BigDecimal amount, Long accountNumber) {
        System.out.println("Client try to deposit # : " + amount + ", " + accountNumber);
        return new RestApi().postAmount(Action.DEPOSIT, accountNumber, amount);
    }

    public boolean withDraw(BigDecimal amount, Long accountNumber) {
        System.out.println("Client try to withDraw # : " + amount + ", " + accountNumber);
        return new RestApi().postAmount(Action.WITHDRAW, accountNumber, amount);
    }
}
