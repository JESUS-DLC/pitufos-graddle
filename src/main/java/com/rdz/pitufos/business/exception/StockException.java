package com.rdz.pitufos.business.exception;

public class StockException extends RuntimeException{
    public StockException() {
        super("elementos insuficientes en el stock");
    }
}
