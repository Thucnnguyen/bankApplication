package com.example.BankApplication.Controller;

import com.example.BankApplication.Client.AccountClient;
import com.example.BankApplication.DTO.LoginAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountClient accountClient;

//    @PostMapping("/login")
//    public Mono<String> Login(@RequestBody LoginAccountDTO loginAccountDTO){
//        return accountClient.Login(loginAccountDTO);
//    }
    @GetMapping("/islogin")
    public Mono<ResponseEntity<String>> isLogin(){
        return accountClient.isLogin()
                .flatMap(isLogin->{
                    return Mono.just(ResponseEntity.ok(isLogin));
                });
    }
}
