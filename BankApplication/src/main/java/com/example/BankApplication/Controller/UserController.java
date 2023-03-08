package com.example.BankApplication.Controller;

import com.example.BankApplication.DTO.AccountDTO;
import com.example.BankApplication.DTO.LoginAccountDTO;
import com.example.BankApplication.DTO.UserAndAccountDTO;
import com.example.BankApplication.DTO.UserDTO;
import com.example.BankApplication.Client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserClient userClient;

    @PostMapping("/addWithAccount")
    public Mono<ResponseEntity<UserDTO>> addUserWithAccount(@RequestBody @Valid UserAndAccountDTO userAndAccountDTO){

        UserDTO user= new UserDTO().name(userAndAccountDTO.getName())
                .address(userAndAccountDTO.getAddress())
                .email(userAndAccountDTO.getEmail())
                .build();

        AccountDTO account= new AccountDTO().userName(userAndAccountDTO.getUserName())
                .accountNumber(userAndAccountDTO.getAccountNumber())
                .accountHolder(userAndAccountDTO.getAccountHolder())
                .password(userAndAccountDTO.getPassword())
                .role(userAndAccountDTO.getRole())
                .build();

        return userClient.addUserAndAccount(user,account)
                .map(u->  ResponseEntity.ok(u));
    }

}
