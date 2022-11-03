package com.nhnacademy.gw1.bank.service;

import com.nhnacademy.gw1.bank.Currency;
import com.nhnacademy.gw1.bank.domain.Bank;
import com.nhnacademy.gw1.bank.domain.Money;
import com.nhnacademy.gw1.bank.exception.UnsupportedUnitRoundUpException;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

@Slf4j
public class BankService {

    public static final int EXCHANGE_RATE_DOLLAR = 1000;
    public static final int EXCHANGE_RATE_EURO = 800;

    public String currencyExchange(Money money, Currency currency) {
         if(money.getUnit().equals("원")) {
            DecimalFormat df = new DecimalFormat("0.00");

            double exchangeMoney = roundingProcess((int) money.getMoney());

            if(currency.equals(Currency.DOLLAR)) {
                exchangeMoney = exchangeMoney / EXCHANGE_RATE_DOLLAR;
                exchangeMoney = processingRoundOff(df, exchangeMoney);
                return exchangeMoney + "$";
            } else if(currency.equals(Currency.EURO)) {
                exchangeMoney = exchangeMoney / EXCHANGE_RATE_EURO;
                exchangeMoney = processingRoundOff(df, exchangeMoney);
                return exchangeMoney + "€";
            }
         }

        throw new UnsupportedUnitRoundUpException(money);
    }

    private double processingRoundOff(DecimalFormat df, double exchangeMoney) {
        exchangeMoney = Double.parseDouble(df.format(exchangeMoney));
        return exchangeMoney;
    }

    public String currencyExchange(Money money) {
        if(money.getUnit().equals("$")) {
            int exchangedMoney = (int) (money.getMoney() * EXCHANGE_RATE_DOLLAR);
            exchangedMoney = roundingProcess(exchangedMoney);

            return exchangedMoney + "원";
        } else if(money.getUnit().equals("€")) {
            int exchangedMoney = (int) (money.getMoney() * EXCHANGE_RATE_EURO);
            exchangedMoney = roundingProcess(exchangedMoney);

            return exchangedMoney + "원";
        }

        throw new UnsupportedUnitRoundUpException(money);
    }

    private int roundingProcess(int exchangedMoney) {
        if (exchangedMoney % 10 >= 5) {
            exchangedMoney += 10 - (exchangedMoney % 10);
        } else {
            exchangedMoney -= exchangedMoney % 10;
        }
        return exchangedMoney;
    }
}
