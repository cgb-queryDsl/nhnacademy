package com.nhnacademy.gw1.parking.domain;

public class Car {

    public User user;
    private String carNumber;
    private CarCode carCode;

    public Car(User user, String carNumber, CarCode carCode) {
        this.user = user;
        this.carNumber = carNumber;
        this.carCode = carCode;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public CarCode getCarCode() {
        return carCode;
    }
}
