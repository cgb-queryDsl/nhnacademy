package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.domain.Car;
import com.nhnacademy.gw1.parking.domain.CarCode;
import com.nhnacademy.gw1.parking.domain.User;
import com.nhnacademy.gw1.parking.exception.InvalidCarNumberException;
import com.nhnacademy.gw1.parking.exception.InvalidEnterGateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.nhnacademy.gw1.parking.domain.CarCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EntranceServiceTest {

    User user;
    EntranceService entranceService;

    @BeforeEach
    void setUp() {
        user = new User("gs", 10_000L);
        entranceService = new EntranceService();
    }

    @Test
    void car_number_scan_success() {
        Car car = new Car(user, "12가 1234", NORMAL);
        assertThat(entranceService.isCarNumberScanned(car)).isTrue();
    }

    @Test
    void car_number_scan_fail() {
        Car car = new Car(user, "Invalid Car Number", NORMAL);

        assertThatThrownBy(() -> entranceService.isCarNumberScanned(car))
                .isInstanceOf(InvalidCarNumberException.class)
                .hasMessageContainingAll("Invalid Car Number", car.getCarNumber());
    }

    @Test
    void car_enter_validGate() {
        String enterGate = "Gate-1";
        Car car = new Car(user, "12가 1234", NORMAL);

        assertThat(entranceService.isEnter(car, enterGate)).isTrue();
    }

    @Test
    void car_enter_inValidGate() {
        String enterGate = "Invalid Gate";
        Car car = new Car(user, "12가 1234", NORMAL);

        assertThatThrownBy(() -> entranceService.isEnter(car, enterGate))
                .isInstanceOf(InvalidEnterGateException.class)
                .hasMessageContainingAll("Invalid", enterGate);
    }
}