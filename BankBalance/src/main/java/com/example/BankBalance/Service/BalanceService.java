package com.example.BankBalance.Service;

import com.example.BankBalance.Entity.Balance;
import com.example.BankBalance.Repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class BalanceService implements IBalanceService{

    @Autowired
    BalanceRepository balanceRepository;

    @Override
    public Mono<Balance> findByAccountId(String accountId) {
        return balanceRepository.findByAccountId(accountId);
    }

    @Override
    public Mono<Balance> save(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Mono<Balance> update(Balance balance) {
        return balanceRepository.save(balance);
    }

    @Override
    public Mono<Void> delete(Balance balance) {
        return balanceRepository.delete(balance);
    }
}
