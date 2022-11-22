package com.nhnacademy.edu.jdbc1.exception;

public class IdDuplicateException extends RuntimeException {

    public IdDuplicateException() {
        super("Primary Key Duplicate Error");
    }
}
