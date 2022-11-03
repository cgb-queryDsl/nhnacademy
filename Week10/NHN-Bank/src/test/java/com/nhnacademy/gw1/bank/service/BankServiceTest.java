package com.nhnacademy.gw1.bank.service;

import com.nhnacademy.gw1.bank.Currency;
import com.nhnacademy.gw1.bank.domain.Bank;
import com.nhnacademy.gw1.bank.domain.Money;
import com.nhnacademy.gw1.bank.exception.UnsupportedUnitRoundUpException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class BankServiceTest {

    BankService bankService;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = Mockito.mock(Bank.class);
        bankService = new BankService();
    }

    @Test
    @DisplayName("달러를 원화로 변경 테스트")
    void dollar_currencyExchange_to_won() {
        Money money = new Money("1$");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("1000원");

        Assertions.assertThat(bankService.currencyExchange(money)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("포인트 있는 달러 원화로 변경 테스트")
    void pointDollar_currencyExchange_to_won() {
        Money money = new Money("5.25$");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("5250원");

        Assertions.assertThat(bankService.currencyExchange(money)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("원화를 달러로 변경 테스트")
    void won_currencyExchange_to_dollar() {
        Money money = new Money("1100원");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("1.1$");

        Assertions.assertThat(bankService.currencyExchange(money, Currency.DOLLAR)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("달러를 원화로 반올림 테스트")
    void dollar_currencyExchange_to_won_then_roundOff() {
        Money money = new Money("5.225$");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("5230원");

        Assertions.assertThat(bankService.currencyExchange(money)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("달러를 원화로 내림 테스트")
    void dollar_currencyExchange_to_won_then_roundDown() {
        Money money = new Money("5.224$");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("5220원");

        Assertions.assertThat(bankService.currencyExchange(money)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("원화를 달러로 반올림 테스트")
    void won_currencyExchange_to_dollar_then_roundOff() {
        Money money = new Money("5226원");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("5.23$");

        Assertions.assertThat(bankService.currencyExchange(money, Currency.DOLLAR)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("원화를 달러로 내림 테스트")
    void won_currencyExchange_to_dollar_then_roundDown() {
        Money money = new Money("5224원");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("5.22$");

        Assertions.assertThat(bankService.currencyExchange(money, Currency.DOLLAR)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("지원되지 않은 화폐 단위 테스트")
    void unSupported_currency_round() {
        Money money = new Money("200￥");

        Assertions.assertThatThrownBy(() -> bankService.currencyExchange(money, Currency.NONE))
                .isInstanceOf(UnsupportedUnitRoundUpException.class)
                .hasMessageContainingAll("Unsupported", money.getUnit());
    }

    @Test
    @DisplayName("유로를 원하로 변경 테스트")
    void euro_currencyExchange_to_won() {
        Money money = new Money("1€");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("800원");

        Assertions.assertThat(bankService.currencyExchange(money)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("원화를 유로로 변경 테스트")
    void won_currencyExchange_to_euro() {
        Money money = new Money("800원");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("1.0€");

        Assertions.assertThat(bankService.currencyExchange(money, Currency.EURO)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("포인트 유로를 원화로 변경 테스트")
    void pointEuro_currencyExchange_to_won() {
        Money money = new Money("5.25€");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("4200원");

        Assertions.assertThat(bankService.currencyExchange(money)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("유로를 원화로 변경 반올림 테스트")
    void euro_currencyExchange_to_won_then_roundOff() {
        Money money = new Money("5.225€");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("4180원");

        Assertions.assertThat(bankService.currencyExchange(money)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("유로를 원화를 변경 내림 테스트")
    void euro_currencyExchange_to_won_then_roundDown() {
        Money money = new Money("5.224€");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("4180원");

        Assertions.assertThat(bankService.currencyExchange(money)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("원화를 유로로 변경 반올림 테스트")
    void won_currencyExchange_to_euro_then_roundOff() {
        Money money = new Money("2337원");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("2.92€");

        Assertions.assertThat(bankService.currencyExchange(money, Currency.EURO)).isEqualTo(bank.getExchangeMoney(money));
    }

    @Test
    @DisplayName("원화를 유로로 변경 내림 테스트")
    void won_currencyExchange_to_euro_then_roundDown() {
        Money money = new Money("2331원");

        Mockito.when(bank.getExchangeMoney(money)).thenReturn("2.91€");

        Assertions.assertThat(bankService.currencyExchange(money, Currency.EURO)).isEqualTo(bank.getExchangeMoney(money));
    }

}