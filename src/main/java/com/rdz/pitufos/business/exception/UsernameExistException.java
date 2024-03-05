package com.rdz.pitufos.business.exception;

public class UsernameExistException extends RuntimeException{
    public UsernameExistException() {
        super("ya existe una cuenta registrada con ese email");
    }
}
