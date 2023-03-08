package com.example.Bank.Service;

import com.example.Bank.Entity.Account;
import com.example.Bank.Repository.AccountRepository;
import com.example.Bank.Repository.LoginHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountService implements IAccountService {
    AccountRepository accountRepository;
    @Autowired
    LoginHistoryRepository loginHistoryRepository;

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:9090")
            .build();

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Mono<Account> getAccountByUserName(String UserName) {
        return accountRepository.findByUserName(UserName);
    }

    @Override
    public Mono<Account> addAccount(Account account) {
//        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }


    @Override
    public Mono<Void> deleteAccount(String userName) {
            return accountRepository.deleteById(userName);
    }

    @Override
    public Mono<Account> updateAccount(Account account) {

        return accountRepository.existsByUserName(account.getUserName())
                .flatMap(exists ->{
                    if(!exists){
                        return Mono.error(new RuntimeException("Account isn't exist!!"));
                    }
                    return accountRepository.save(account);
                });

    }


    @Override
    public Flux<Account> getAllAccount() {
        return accountRepository.findAll();
    }



    public Mono<Boolean> isAccountExist(String userName){
        return accountRepository.existsByUserName(userName).log();
    }

    public Mono<String> isLogin(){
        Mono<Authentication>  authentication = ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication);

        return authentication
                .map(Authentication::getName).log();
    }

    @Override
    public Mono<Account> findByUserName(String username) {
        return accountRepository.findByUserName(username);
    }

}
