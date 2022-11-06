package com.nhnacademy.gw1.parking.exception;

public class InvalidParkZoneException extends RuntimeException {

    public InvalidParkZoneException(String parkZone) {
        super("Invalid Park Zone : " + parkZone);
    }
}
