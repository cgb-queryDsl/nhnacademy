package com.nhnacademy.gw1.parking.domain;

public class ParkSpace {

    private Car car;
    private String parkZone;

    public ParkSpace(Car car, String parkZone) {
        this.car = car;
        this.parkZone = parkZone;
    }

    public Car getCar() {
        return car;
    }

    public String getParkZone() {
        return parkZone;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "car=" + car +
                ", parkZone='" + parkZone + '\'' +
                '}';
    }
}
