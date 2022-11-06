package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.domain.*;
import com.nhnacademy.gw1.parking.exception.InvalidParkZoneException;
import com.nhnacademy.gw1.parking.exception.LargeCarException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.nhnacademy.gw1.parking.domain.CarCode.*;
import static com.nhnacademy.gw1.parking.domain.Payco.*;
import static org.assertj.core.api.Assertions.*;

class ParkServiceTest {

    User user;
    ParkService parkService;

    @BeforeEach
    void setUp() {
        user = new User("gs", 10_000L);
        parkService = new ParkService();
    }

    @Test
    @DisplayName("없는 주차장 구역에 주차하는 테스트")
    void fail_park_in_nonExist_parkingArea() {
        Car car = new Car(user, "12가 1234", NORMAL);

        assertThatThrownBy(() -> parkService.park(car, "NoZone-3"))
                .isInstanceOf(InvalidParkZoneException.class)
                .hasMessageContainingAll("Invalid Park Zone", "NoZone-3");
    }

    @ParameterizedTest
    @DisplayName("주차장 구역에 주차하는 테스트")
    @ValueSource(strings = {"A-1", "A-2"})
    void success_park_in_exist_parkingArea(String parkZone) {
        Car car = new Car(user, "12가 1234", NORMAL);
        ParkSpace parkSpace = parkService.park(car, parkZone);

        assertThat(parkSpace.getCar()).isEqualTo(car);
        assertThat(parkSpace.getParkZone()).isEqualTo(parkZone);
    }

    @Test
    @DisplayName("대형, 트럭은 주차가 불가능")
    void largeCar_cannot_park() {
        Car car = new Car(user, "12가 1234", LARGE);

        assertThatThrownBy(() -> parkService.park(car, "A-1"))
                .isInstanceOf(LargeCarException.class)
                .hasMessageContainingAll("Large car");
    }

}