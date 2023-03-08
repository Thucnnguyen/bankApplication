package com.example.Bank.Repository;

import com.example.Bank.Entity.LoginHistory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LoginHistoryRepository extends ReactiveMongoRepository<LoginHistory,Long> {
    public Mono<LoginHistory> findByAccountId(String accountId);
}
