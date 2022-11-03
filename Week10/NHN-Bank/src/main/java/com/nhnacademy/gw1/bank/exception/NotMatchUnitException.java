package com.nhnacademy.gw1.bank.exception;

public class NotMatchUnitException extends RuntimeException {
    public NotMatchUnitException(String unit1, String unit2) {
        super("unit is not match : " + unit1 + " != " + unit2);
    }
}
