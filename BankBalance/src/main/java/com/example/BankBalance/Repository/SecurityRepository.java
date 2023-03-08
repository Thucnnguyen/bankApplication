package com.example.BankBalance.Repository;

import com.example.BankBalance.Entity.Security;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository

public interface SecurityRepository extends ReactiveMongoRepository<Security,Long> {
    Mono<Security> findByAccountId(String accountNumber);
    Flux<Security> findByVerificationCodeExpirationBefore(LocalDateTime localDateTime);
}
