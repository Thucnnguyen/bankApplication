package com.example.Bank.Service;

import com.example.Bank.Entity.Account;
import com.example.Bank.Entity.User;
import org.hibernate.sql.Update;
import reactor.core.publisher.Mono;

public interface IUserService {
    public Mono<User> addUser(User user);
    public Mono<User> updateUser(User User);
    public Mono<Void> deleteUser(User user);
    public Mono<Void> deleteUserById(String id);
    public Mono<User> getUser(String UserId);
    public Mono<User> getUserByName(String name);
    public Mono<User> getUserbyAccountNumber(String accountNumber);
    public Mono<User> addUserAndAccount(User user, Account account);

}
