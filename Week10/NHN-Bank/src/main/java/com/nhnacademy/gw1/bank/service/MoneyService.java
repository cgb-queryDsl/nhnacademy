package com.nhnacademy.gw1.bank.service;

import com.nhnacademy.gw1.bank.domain.Money;
import com.nhnacademy.gw1.bank.exception.MoneyIsNegativeException;
import com.nhnacademy.gw1.bank.exception.NotMatchUnitException;

public class MoneyService {

    public String add(Money money1, Money money2) {
        if(!isMatchUnit(money1, money2)) {
            throw new NotMatchUnitException(money1.getUnit(), money2.getUnit());
        }

        if(isWon(money1)) {
            return (int) addMoney(money1, money2) + money1.getUnit();
        }
        return Math.round(addMoney(money1, money2) * 100) /100.0 + money1.getUnit();
    }

    public String subtract(Money money1, Money money2) {
        negativeMoneyCheck(money1, money2);
        return subtractMoney(money1, money2) + money1.getUnit();
    }

    private void negativeMoneyCheck(Money money1, Money money2) {
        if(subtractMoney(money1, money2) < 0) {
            throw new MoneyIsNegativeException(subtractMoney(money1, money2));
        }
    }

    public boolean isEqualed(Money money1, Money money2) {
        return money1.getMoney() == money2.getMoney();
    }

    private boolean isWon(Money money1) {
        return money1.getUnit().equals("원");
    }

    private boolean isMatchUnit(Money money1, Money money2) {
        return money1.getUnit().equals(money2.getUnit());
    }

    private double addMoney(Money money1, Money money2) {
        return money1.getMoney() + money2.getMoney();
    }

    private double subtractMoney(Money money1, Money money2) {
        return money1.getMoney() - money2.getMoney();
    }

}
