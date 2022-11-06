package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.domain.Car;
import com.nhnacademy.gw1.parking.domain.CarCode;
import com.nhnacademy.gw1.parking.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.nhnacademy.gw1.parking.domain.CarCode.*;
import static com.nhnacademy.gw1.parking.domain.Payco.MEMBER;
import static com.nhnacademy.gw1.parking.domain.Payco.NONE;
import static org.assertj.core.api.Assertions.assertThat;

class ExitServiceV2Test {

    ExitServiceV2 exitService;

    @BeforeEach
    void setUp() {
        exitService = new ExitServiceV2();
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void success_pay_parkFee_smallCar(long parkTime, long parkFee) {
        User user = new User("gs", 50_000L);
        Car car = new Car(user, "12가 1234", SMALL);

        assertThat(exitService.pay(user, parkTime, car)).isEqualTo(parkFee);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.arguments(1_800L, 0L),
                Arguments.arguments(1_801L, 500L),
                Arguments.arguments(3_600L, 500L),
                Arguments.arguments(4_800L, 1_000L),
                Arguments.arguments(19_400L, 7_250L),
                Arguments.arguments(21_600L, 7_500L),
                Arguments.arguments(259_200L, 22_500L)  // 3일
        );
    }

    @Test
    void car_exit_validExit() {
        User user = new User("gs", 99_999L);
        Car car = new Car(user, "12가 1234", SMALL);
        String exit = "Exit-1";

        assertThat(exitService.isExit(user, 2_000L, car, exit)).isTrue();
    }


    @Test
    void paycoMember_pay() {
        User paycoMember = new User("gs", 50_000L, MEMBER);
        Car car = new Car(paycoMember, "12가 1234", SMALL);

        assertThat(exitService.pay(paycoMember, 4_800L, car)).isEqualTo(900L);
    }

    @Test
    void nonPaycoMember_pay() {
        User nonPaycoMember = new User("gs", 50_000L, NONE);
        Car car = new Car(nonPaycoMember, "12가 1234", SMALL);

        assertThat(exitService.pay(nonPaycoMember, 4_800L, car)).isEqualTo(1_000L);
    }
}