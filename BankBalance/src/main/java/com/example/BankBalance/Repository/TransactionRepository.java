package com.example.BankBalance.Repository;

import com.example.BankBalance.Entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction,Long> {
    public Mono<Transaction> findById(String id);
    public Mono<Boolean> existsById(String id);
    public Flux<Transaction> findByReceiveAccountIdOrDepositAccountId(String accountId);
    public Flux<Transaction> findByDateBefore(LocalDateTime localDateTime);
}
