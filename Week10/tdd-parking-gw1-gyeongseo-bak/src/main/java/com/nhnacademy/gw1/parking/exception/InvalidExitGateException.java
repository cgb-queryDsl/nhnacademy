package com.nhnacademy.gw1.parking.exception;

public class InvalidExitGateException extends RuntimeException {

    public InvalidExitGateException(String exit) {
        super("Invalid Exit Gate : " + exit);
    }
}
