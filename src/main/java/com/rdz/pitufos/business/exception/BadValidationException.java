package com.rdz.pitufos.business.exception;

public class BadValidationException extends RuntimeException{
    public BadValidationException() {
        super("bad credentials");
    }
}
