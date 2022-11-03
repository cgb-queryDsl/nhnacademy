package com.nhnacademy.gw1.bank.domain;

import com.nhnacademy.gw1.bank.exception.MoneyIsNegativeException;

import java.text.DecimalFormat;

public class Money {
    private double money;
    private String unit;

    public Money(String money) {
        if (isContainsWon(money)) {
            this.unit = "원";
            money = subtractUnit(money);
            this.money = doubleTypeConversion(money);
        } else if (isContainsDollar(money)) {
            this.unit = "$";
            money = subtractUnit(money);
            settingMoneyToDoubleWithThreeDecimal(money);
        } else if (isContainsEuro(money)) {
            this.unit = "€";
            money = subtractUnit(money);
            settingMoneyToDoubleWithThreeDecimal(money);
        } else {
            this.unit = "Unsupported";
        }

        negativeMoneyVerification();
    }

    private void settingMoneyToDoubleWithThreeDecimal(String money) {
        DecimalFormat df = new DecimalFormat("0.000");
        this.money = doubleTypeConversion(df.format(doubleTypeConversion(money)));
    }

    private double doubleTypeConversion(String money) {
        return Double.parseDouble(money);
    }

    private String subtractUnit(String money) {
        if(isContainsWon(money)) {
            money = money.replace("원", "");
        }
        else if (isContainsDollar(money)) {
            money = money.replace("$", "");
        } else if (isContainsEuro(money)) {
            money = money.replace("€", "");
        }
        return money;
    }

    private void negativeMoneyVerification() {
        if(this.money < 0) {
            throw new MoneyIsNegativeException(this.money);
        }
    }

    private boolean isContainsDollar(String money) {
        return money.contains("$");
    }

    private boolean isContainsWon(String money) {
        return money.contains("원");
    }

    private static boolean isContainsEuro(String money) {
        return money.contains("€");
    }
    public double getMoney() {
        return money;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                ", unit='" + unit + '\'' +
                '}';
    }
}
