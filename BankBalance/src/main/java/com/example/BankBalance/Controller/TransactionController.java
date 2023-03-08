package com.example.BankBalance.Controller;

import com.example.BankBalance.DTO.TransactionDetail;
import com.example.BankBalance.Entity.Transaction;
import com.example.BankBalance.Service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RequestMapping("tran")
@RestController
public class TransactionController {
    @Autowired
    ITransactionService transactionService;

    private Sinks.Many<Transaction> transactionSinks;

    public TransactionController() {
        this.transactionSinks = Sinks.many().multicast().onBackpressureBuffer();
    }

    @PostMapping("/transferMoney")
    public Mono<Transaction> transferMoney(@RequestBody Transaction transaction){
        return transactionService.transfer(transaction);
    }

    @PostMapping("/verify")
    public Mono<Transaction> verify(@RequestBody TransactionDetail transactionDetail){
        var transaction= transactionService.
                verifyTransaction(transactionDetail.getVerificationCode(),transactionDetail.getTransactionId());
        if(transaction!=null){

        }

    }
}
