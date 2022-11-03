package com.nhnacademy.gw1.bank.service;

import com.nhnacademy.gw1.bank.domain.Money;
import com.nhnacademy.gw1.bank.exception.MoneyIsNegativeException;
import com.nhnacademy.gw1.bank.exception.NotMatchUnitException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoneyServiceTest {

    MoneyService moneyService;

    @BeforeEach
    void setUp() {
        moneyService = new MoneyService();
    }

    @Test
    void add_money_won() {
        Money money1 = new Money("1000원");
        Money money2 = new Money("2000원");

        Assertions.assertThat(moneyService.add(money1, money2)).isEqualTo("3000원");
    }

    @Test
    void check_money_equals() {
        Money money1 = new Money("2000원");
        Money money2 = new Money("2000원");

        Assertions.assertThat(moneyService.isEqualed(money1, money2)).isTrue();
    }

    @Test
    void check_money_not_equals() {
        Money money1 = new Money("2000원");
        Money money2 = new Money("1000원");

        Assertions.assertThat(moneyService.isEqualed(money1, money2)).isFalse();
    }

    @Test
    void check_money_negative() {
        Assertions.assertThatThrownBy(() -> new Money("-1000원"))
                .isInstanceOf(MoneyIsNegativeException.class)
                .hasMessageContainingAll("negative");
    }

    @Test
    void add_money_dollar() {
        Money money1 = new Money("5$");
        Money money2 = new Money("4$");

        Assertions.assertThat(moneyService.add(money1, money2)).isEqualTo("9.0$");
    }

    @Test
    void add_not_match_unit() {
        Money money1 = new Money("1000원");
        Money money2 = new Money("2$");

        Assertions.assertThatThrownBy(() -> moneyService.add(money1, money2))
                .isInstanceOf(NotMatchUnitException.class)
                .hasMessageContainingAll("not match", money1.getUnit(), money2.getUnit());
    }

    @Test
    void success_subtract_dollar() {
        Money money1 = new Money("10$");
        Money money2 = new Money("6$");

        Assertions.assertThat(moneyService.subtract(money1, money2)).isEqualTo("4.0$");
    }

    @Test
    void failed_subtract_dollar() {
        Money money1 = new Money("5$");
        Money money2 = new Money("6$");

        Assertions.assertThatThrownBy(() -> moneyService.subtract(money1, money2))
                .isInstanceOf(MoneyIsNegativeException.class)
                .hasMessageContainingAll("negative");
    }

    @Test
    void add_money_dollarWithPoint() {
        Money money1 = new Money("5.39$");
        Money money2 = new Money("3.76$");

        Assertions.assertThat(moneyService.add(money1, money2)).isEqualTo("9.15$");
    }

    @Test
    void add_money_euro() {
        Money money1 = new Money("2€");
        Money money2 = new Money("4€");

        Assertions.assertThat(moneyService.add(money1, money2)).isEqualTo("6.0€");
    }

    @Test
    void add_money_euroWithPoint() {
        Money money1 = new Money("5.39€");
        Money money2 = new Money("3.76€");

        Assertions.assertThat(moneyService.add(money1, money2)).isEqualTo("9.15€");
    }

    @Test
    void success_subtract_money_euro() {
        Money money1 = new Money("10€");
        Money money2 = new Money("6€");

        Assertions.assertThat(moneyService.subtract(money1, money2)).isEqualTo("4.0€");
    }

    @Test
    void check_money_negative_euro() {
        Assertions.assertThatThrownBy(() -> new Money("-1000€"))
                .isInstanceOf(MoneyIsNegativeException.class)
                .hasMessageContainingAll("negative");
    }

}