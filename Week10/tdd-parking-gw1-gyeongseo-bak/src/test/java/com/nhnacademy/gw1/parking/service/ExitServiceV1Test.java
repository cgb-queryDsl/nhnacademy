package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.domain.User;
import com.nhnacademy.gw1.parking.exception.InvalidExitGateException;
import com.nhnacademy.gw1.parking.exception.NotEnoughMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExitServiceV1Test {

    ExitServiceV1 exitService;

    @BeforeEach
    void setUp() {
        exitService = new ExitServiceV1();
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void success_pay_parkFee(long parkTime, long parkFee) {
        User user = new User("gs", 50_000L);

        assertThat(exitService.pay(user, parkTime)).isEqualTo(parkFee);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.arguments(1_800L, 1_000L),
                Arguments.arguments(1_801L, 1_500L),
                Arguments.arguments(3_000L, 2_000L),
                Arguments.arguments(3_660L, 3_000L),
                Arguments.arguments(11_000L, 9_000L),
                Arguments.arguments(21_600L, 10_000L),
                Arguments.arguments(89_400L, 12_000L),      // 하루 지난 케이스R
                Arguments.arguments(99_000L, 20_000L),
                Arguments.arguments(99_001L, 20_000L),
                Arguments.arguments(259_200L, 30_000L)
        );
    }

    @Test
    void fail_pay_parkFee() {
        User user = new User("gs", 1_000L);

        assertThatThrownBy(() -> exitService.pay(user, 2_000L))
                .isInstanceOf(NotEnoughMoneyException.class)
                .hasMessageContainingAll("enough", String.valueOf(user.getMoney()));
    }

    @Test
    void car_exit_validExit() {
        User user = new User("gs", 99_999L);
        String exit = "Exit-1";

        assertThat(exitService.isExit(user, 2_000L, exit)).isTrue();
    }

    @Test
    void car_exit_inValidExit() {
        User user = new User("gs", 99_999L);
        String exit = "invalid exit";

        assertThatThrownBy(() -> exitService.isExit(user, 2_000L, exit))
                .isInstanceOf(InvalidExitGateException.class)
                .hasMessageContainingAll("Invalid", exit);
    }

}