package com.example.Bank.Repository;

import com.example.Bank.Entity.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, Long> {

    Mono<Account> findByUserName(String userName);

    Mono<Void> deleteById(String userName);

    Mono<Boolean> existsByUserName(String userName);

    Mono<Account> findByAccountNumber(String accountNumber);

    Mono<Account> findById(String id);
}
