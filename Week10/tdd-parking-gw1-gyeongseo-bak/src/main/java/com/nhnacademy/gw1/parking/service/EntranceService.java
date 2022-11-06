package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.domain.Car;
import com.nhnacademy.gw1.parking.repository.Entrance;
import com.nhnacademy.gw1.parking.exception.InvalidCarNumberException;
import com.nhnacademy.gw1.parking.exception.InvalidEnterGateException;

public class EntranceService {

    public boolean isEnter(Car car, String gate) {
        if (isValidEntrance(gate)) {
            isCarNumberScanned(car);
            return true;
        }
        throw new InvalidEnterGateException(gate);
    }

    private boolean isValidEntrance(String gate) {
        Entrance entrance = new Entrance();
        if (!entrance.getEntrance(gate).contains("invalid")) {
            return true;
        }
        return false;
    }

    public boolean isCarNumberScanned(Car car) {
        isValidCarNumber(car);
        return true;
    }

    private void isValidCarNumber(Car car) {
        if (car.getCarNumber().contains("Invalid")) {
            throw new InvalidCarNumberException(car.getCarNumber());
        }
    }

}
