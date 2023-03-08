package com.example.BankBalance.Repository;

import com.example.BankBalance.Entity.Balance;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BalanceRepository extends ReactiveMongoRepository<Balance,Long> {
    public Mono<Balance> findByAccountId(String id);

}
