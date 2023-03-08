package com.example.BankApplication.ExceptionHandler;

import com.example.BankApplication.Exception.AccountException;
import com.example.BankApplication.Exception.BalanceException;
import com.example.BankApplication.Exception.UserException;
import jdk.jshell.spi.ExecutionControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHanlder {
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<String>handlerClientException(AccountException ex){
        log.error("Exception caught in handleClientException :{}",ex.getMessage(),ex);
        log.info("Status value is : {}", ex.getStatusCode());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatusCode())).body(ex.getMessage());
    }
    @ExceptionHandler(BalanceException.class)
    public ResponseEntity<String>handlerBalanceClientException(BalanceException ex){
        log.error("Exception caught in handleClientException :{}",ex.getMessage(),ex);
        log.info("Status value is : {}", ex.getStatusCode());
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatusCode())).body(ex.getMessage());
    }
    @ExceptionHandler({RuntimeException.class, HttpClientErrorException.NotFound.class})
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        log.error("Exception caught in handleClientException :  {} " ,ex.getMessage(),  ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handlerBodyError(WebExchangeBindException ex){
        log.error("Exception caught in handleClientException :  {} " ,ex.getMessage(),  ex);
        var errors= ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .sorted()
                .collect(Collectors.joining("\n"));
        log.error("errorList : {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
