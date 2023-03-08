package com.example.BankBalance.Service;

import com.example.BankBalance.Entity.Security;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISecurityService {

    public Mono<Security> save(Security security);
    public Mono<String> getVerificationCode(String id);
    public String verificationCode(String userName);
    public Mono<Security> update(Security security);
    public Flux<Security> findSecurityFlux();
}
