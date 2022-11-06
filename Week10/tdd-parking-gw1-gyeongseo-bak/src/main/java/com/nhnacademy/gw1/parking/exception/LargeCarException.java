package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.domain.Car;
import com.nhnacademy.gw1.parking.domain.CarCode;

public class LargeCarException extends RuntimeException {

    public LargeCarException(Car car) {
        super("Large car can't park : " + car.getCarCode());
    }
}
