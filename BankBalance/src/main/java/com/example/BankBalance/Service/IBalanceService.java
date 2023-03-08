package com.example.BankBalance.Service;

import com.example.BankBalance.Entity.Balance;
import reactor.core.publisher.Mono;

public interface IBalanceService {
    public Mono<Balance> findByAccountId(String accountId);
    public Mono<Balance> save(Balance balance);
    public Mono<Balance> update(Balance balance);
    public Mono<Void> delete(Balance balance);
}
