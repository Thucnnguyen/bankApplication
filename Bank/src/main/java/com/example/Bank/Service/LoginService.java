package com.example.Bank.Service;

import com.example.Bank.Entity.LoginHistory;
import com.example.Bank.Repository.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class LoginService implements ILoginHistoryService {
    @Autowired
    LoginHistoryRepository loginHistoryRepository;

    @Override
    public Mono<LoginHistory> save(LoginHistory loginHistory) {
        return  loginHistoryRepository.save(loginHistory);
    }

    @Override
    public Mono<LoginHistory> getLoginhistoryByAccountId(String id) {
        return loginHistoryRepository.findByAccountId(id);
    }
}
