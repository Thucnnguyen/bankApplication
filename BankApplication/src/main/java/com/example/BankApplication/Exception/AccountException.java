package com.example.BankApplication.Exception;

import lombok.Data;

@Data
public class AccountException extends RuntimeException{
    private String message;
    private Integer statusCode;

    public AccountException(String message, Integer statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

}
