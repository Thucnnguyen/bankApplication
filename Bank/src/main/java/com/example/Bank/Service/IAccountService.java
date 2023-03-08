package com.example.Bank.Service;

import com.example.Bank.Entity.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountService {
    public Mono<Account> getAccountByUserName(String UserName);
//    public Mono<Double> getAccountByUserNameAndPassword(String userName,String password);
    public Mono<Account> addAccount(Account account);
    public Mono<Void> deleteAccount(String userName);
    public Mono<Account> updateAccount(Account account);
//    public Mono<Account> findByAccountNumber(String accountNumber);
    public Flux<Account> getAllAccount();
    public Mono<Boolean> isAccountExist(String userName);
    public Mono<String> isLogin();

    public Mono<Account> findByUserName(String username);
}
