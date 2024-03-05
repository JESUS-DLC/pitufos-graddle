package com.rdz.pitufos.business.exception;

public class EmailExistException extends RuntimeException{
    public EmailExistException() {
        super("ya existe una cuenta registrada con ese correo");
    }
}
