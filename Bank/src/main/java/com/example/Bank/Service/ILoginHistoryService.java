package com.example.Bank.Service;

import com.example.Bank.Entity.LoginHistory;
import reactor.core.publisher.Mono;

public interface ILoginHistoryService {
    public Mono<LoginHistory> save(LoginHistory loginHistory);
    public Mono<LoginHistory> getLoginhistoryByAccountId(String accountId);
}
