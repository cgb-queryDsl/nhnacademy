package com.nhnacademy.bank;

import java.math.BigDecimal;

public class Application {

    private static final Long ACCOUNT_NUMBER = 123L;

    public static void main(String[] args) {
        System.out.println("Application.main() callstack start");
        var atm = new Atm();

        boolean rt = atm.deposit(BigDecimal.valueOf(100.1), ACCOUNT_NUMBER);

        System.out.println("Application.main() callstack end " + rt);
    }
}
