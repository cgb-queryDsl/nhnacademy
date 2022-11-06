package com.nhnacademy.gw1.parking.exception;

public class InvalidEnterGateException extends RuntimeException {
    public InvalidEnterGateException(String gate) {
        super("Invalid Enter Gate : " + gate);
    }
}
