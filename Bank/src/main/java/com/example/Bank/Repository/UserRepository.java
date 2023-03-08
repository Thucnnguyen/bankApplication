package com.example.Bank.Repository;

import com.example.Bank.Entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,Long> {
    public Mono<User> findById(String id);
    public Mono<User> findByName(String id);
    public Mono<Void> deleteById(String id);
    public Mono<User> findByAccountId(String accountNumber);

}
