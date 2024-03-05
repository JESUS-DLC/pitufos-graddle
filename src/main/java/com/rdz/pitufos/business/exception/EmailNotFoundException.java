package com.rdz.pitufos.business.exception;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException() {
        super("no existe una cuenta asociada al email proporcionado");
    }
}
