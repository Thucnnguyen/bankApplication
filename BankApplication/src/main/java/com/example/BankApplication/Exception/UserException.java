package com.example.BankApplication.Exception;

import lombok.Data;

@Data
public class UserException  extends RuntimeException{
    private String message;
    private Integer statusCode;

    public UserException(String message, String message1, Integer statusCode) {
        super(message);
        this.message = message1;
        this.statusCode = statusCode;
    }
}
