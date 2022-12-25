package com.nhnacademy.edu.finaltest.exception;

public class FileReadException extends RuntimeException {

    public FileReadException(String filePath) {
        super("cannot read through file path : " + filePath);
    }
}
