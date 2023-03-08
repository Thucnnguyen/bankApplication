package com.example.Bank.Controller;

import com.example.Bank.DTO.LoginAccountDTO;
import com.example.Bank.Entity.Account;
//import com.example.Bank.Service.AuthenticationService;
import com.example.Bank.Service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.security.auth.login.AccountException;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    IAccountService accountService;





    @PostMapping("/add")
    public Mono<ResponseEntity<Account>> addAccount(@RequestBody Account account) {
        return accountService.addAccount(account)
                .map(savedAccount -> ResponseEntity.ok(savedAccount));
//                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping("/{userName}")
    public Mono<ResponseEntity<Account>> getAccount(@PathVariable("userName") String userName){
        return accountService.getAccountByUserName(userName)
                .map(account -> ResponseEntity.ok().body(account))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/islogin")
    public Mono<String> isLogin(){
        return accountService.isLogin();
    }
    @PutMapping("/update")
    public Mono<Account> updateAccount(@RequestBody Account account){
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/{username}")
    public Mono<Void> deleteAccount(@PathVariable("username") String userName ){
        return accountService.deleteAccount(userName);
    }

    @GetMapping("/all")
    public Flux<Account> getAll( ){
        return accountService.getAllAccount();
    }

    @GetMapping("/isExist/{username}")
    public Mono<Boolean> isAccountExist(@PathVariable("username") String username){
        return accountService.isAccountExist(username);
    }

}
