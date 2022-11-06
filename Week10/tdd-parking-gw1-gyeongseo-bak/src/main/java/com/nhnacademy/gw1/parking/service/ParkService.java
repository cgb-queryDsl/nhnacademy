package com.nhnacademy.gw1.parking.service;

import com.nhnacademy.gw1.parking.domain.Car;
import com.nhnacademy.gw1.parking.domain.ParkSpace;
import com.nhnacademy.gw1.parking.repository.ParkingSpaces;
import com.nhnacademy.gw1.parking.exception.InvalidParkZoneException;
import com.nhnacademy.gw1.parking.exception.LargeCarException;

import static com.nhnacademy.gw1.parking.domain.CarCode.LARGE;

public class ParkService {

    private ParkingSpaces parkingSpaces = new ParkingSpaces();

    public ParkSpace park(Car car, String parkZone) {
        if (isValidParkZone(parkZone)) {
            isLargeCar(car);
            return new ParkSpace(car, parkZone);
        }

        throw new InvalidParkZoneException(parkZone);
    }

    private void isLargeCar(Car car) {
        if (car.getCarCode() == LARGE) {
            throw new LargeCarException(car);
        }
    }

    private boolean isValidParkZone(String parkZone) {
        return !parkingSpaces.getParkSpace(parkZone).contains("invalid");
    }

}
