package com.nhnacademy.bank;

import com.nhnacademy.bank.client.Atm;
import java.math.BigDecimal;

/**
 * Application.
 */
public class Application {

    private static final Long ACCOUNT_NUMBER = 123L;

    /**
     * entry point main method.
     *
     * @param args system variable.
     */
    public static void main(String[] args) {
        System.out.println("Application.main() callstack start");
        var atm = new Atm();

        boolean rt = atm.deposit(BigDecimal.valueOf(100.1), ACCOUNT_NUMBER);

        System.out.println("Application.main() callstack end " + rt);
    }
}
