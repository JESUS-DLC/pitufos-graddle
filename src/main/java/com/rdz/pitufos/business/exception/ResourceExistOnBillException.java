package com.rdz.pitufos.business.exception;

public class ResourceExistOnBillException extends RuntimeException{
    public ResourceExistOnBillException() {
        super("el producto ya se encuentra en la cuenta");
    }
}
