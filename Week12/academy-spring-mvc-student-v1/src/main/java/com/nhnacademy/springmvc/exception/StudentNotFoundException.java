package com.nhnacademy.springmvc.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long studentId) {
        super("Not Found StudentId : " + studentId);
    }
}
