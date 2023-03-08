package com.example.Bank.Service;

import com.example.Bank.Entity.Account;
import com.example.Bank.Entity.User;
import com.example.Bank.Repository.AccountRepository;
import com.example.Bank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;
    @Override
    public Mono<User> addUser(User user) {
        return
            accountRepository.existsByUserName(user.getAccountId()).flatMap(b ->{
                if(!b){
                    return Mono.error(new RuntimeException("Account is not exist !!"));
                }
                return userRepository.save(user);
            });
//                .doOnNext(u ->{
//                    if(!u.getAccountId().isEmpty()){
//                        if(accountRepository.findByAccountNumber(u.getAccount().getAccountNumber())== null){
//                            accountRepository.save(u.getAccount());
//                        }
//                    }
//                });
    }

    @Override
    public Mono<User> updateUser(User user) {

//        return userRepository.findById(user.getId())
//                .flatMap(u->{
//                    user.setAccount(u.getAccount());
//                    return userRepository.save(user);
//                });
        return userRepository.save(user);
    }

    @Override
    public Mono<Void> deleteUser(User user) {
        return userRepository.delete(user);
    }

    @Override
    public Mono<Void> deleteUserById(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<User> getUser(String UserId) {
        return userRepository.findById(UserId);
    }

    @Override
    public Mono<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Mono<User> getUserbyAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .flatMap(a->{
                    return userRepository.findByAccountId(a.getUserName());
                });
    }

    @Override
    public Mono<User> addUserAndAccount(User user, Account account) {
        return Mono.defer(()->{
            Mono<Account> accountMono= accountService.addAccount(account);
            return accountMono.zipWith(Mono.fromCallable(()->user));
        }).flatMap(tuple->{
            Account a= tuple.getT1();
            User u= tuple.getT2();
            u.setAccountId(a.getUserName());
            return userRepository.save(u);
        });
    }


}
