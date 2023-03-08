package com.example.BankApplication.Exception;

import lombok.Data;

@Data
public class BalanceException extends RuntimeException{
    private String message;
    private Integer statusCode;

    public BalanceException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }
}
