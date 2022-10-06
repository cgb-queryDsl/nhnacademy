package com.nhnacademy.bank.server;

import com.nhnacademy.bank.domain.Account;
import com.nhnacademy.bank.domain.Money;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

/**
 * RestApi를 구현한 class.
 */
public class RestApi {

    /**
     * postAmount().
     *
     * @param action        행동.
     * @param accountNumber 계좌번호.
     * @param amount        돈.
     * @return true/false.
     */
    public boolean postAmount(Action action, Long accountNumber, BigDecimal amount) {
        System.out.println("Server try to postAmount # : post Amount=" + action
                + ", accountNumber=" + accountNumber + ", amount=" + amount);

        var account = new Account();

        if (Action.DEPOSIT == action) {
            account.deposit(new Money(amount, Currency.getInstance(Locale.US)));
        } else if (Action.WITHDRAW == action) {
            account.withdraw(new Money(amount, Currency.getInstance(Locale.US)));
        }

        return true;
    }
}
