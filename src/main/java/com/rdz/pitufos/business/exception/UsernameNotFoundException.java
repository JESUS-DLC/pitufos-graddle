package com.rdz.pitufos.business.exception;

public class UsernameNotFoundException extends RuntimeException{
    public UsernameNotFoundException() {
        super("no existe una cuenta con el usuario proporcionado");
    }
}
