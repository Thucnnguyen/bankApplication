package com.example.BankBalance.Service;

import com.example.BankBalance.Entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionService {
    public Mono<Transaction> getTransactionById(String Id);

    public Mono<Transaction> transfer(Transaction transaction);

    public Mono<Transaction> deleteTransfer(String accountNumber);

    public Mono<Transaction> updateTransaction(Transaction transaction);

    public Mono<Transaction> verifyTransaction(String verificationCode,String transactionId);

    public Flux<Transaction> getTransactionByAccountId(String accountId);
}
