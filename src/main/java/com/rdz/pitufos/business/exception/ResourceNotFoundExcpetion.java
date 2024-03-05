package com.rdz.pitufos.business.exception;

public class ResourceNotFoundExcpetion extends RuntimeException{
    public ResourceNotFoundExcpetion() {
        super("recurso no encontrado");
    }
}
