package com.nhnacademy.gw1.parking.exception;

public class InvalidCarNumberException extends RuntimeException {

    public InvalidCarNumberException(String carNumber) {
        super("Invalid Car Number : " + carNumber);
    }
}
