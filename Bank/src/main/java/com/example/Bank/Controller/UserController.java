package com.example.Bank.Controller;

import com.example.Bank.DTO.UserAndAccountDTO;
import com.example.Bank.Entity.Account;
import com.example.Bank.Entity.User;
import com.example.Bank.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/add")
    public Mono<User> addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("/get/{id}")
    public Mono<User> getUser(@PathVariable("id") String id){
        return userService.getUser(id);
    }
    @GetMapping("/get/accountNumber/{accountNumber}")
    public Mono<User> getUserByaccountNumber(@PathVariable("accountNumber") String accountNumber){
        return userService.getUserbyAccountNumber(accountNumber);
    }
    @PostMapping("/addWithAccount")
    public Mono<ResponseEntity<User>> addUserWithAccount(@RequestBody UserAndAccountDTO userAndAccountDTO){

        User user= new User().name(userAndAccountDTO.getName())
                .address(userAndAccountDTO.getAddress())
                .email(userAndAccountDTO.getEmail())
                .build();

        Account account= new Account().userName(userAndAccountDTO.getUserName())
                .accountNumber(userAndAccountDTO.getAccountNumber())
                .accountHolder(userAndAccountDTO.getAccountHolder())
                .password(userAndAccountDTO.getPassword())
                .role(userAndAccountDTO.getRole())
                .build();
        return userService.addUserAndAccount(user,account)
                .map(u->  ResponseEntity.ok(u));
    }
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") String id){
        return userService.deleteUserById(id);
    }
    @PutMapping("/update")
    public Mono<User> updateUSer(@RequestBody User user){
        return userService.updateUser(user);
    }
}
