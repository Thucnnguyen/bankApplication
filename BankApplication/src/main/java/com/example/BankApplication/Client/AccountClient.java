package com.example.BankApplication.Client;

import com.example.BankApplication.DTO.*;
import com.example.BankApplication.Exception.AccountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AccountClient implements ReactiveUserDetailsService {
    @Value("${restClient.accountUrl}")
    private String URLACCOUNT;
    @Value("${restClient.balanceUrl}")
    private  String URLTRANSACTION;
    @Autowired
    WebClient webClient;
    @Autowired
    private PasswordEncoder encode;
    public Mono<AccountDTO> getAccountByUserName(String username){

        return webClient.get()
                .uri(URLACCOUNT +"/account/{username}",username)
                .retrieve()
                .bodyToMono(AccountDTO.class);
    }

    public Mono<AccountDTO> addAccount(AccountDTO accountDto){
        // Check if the account already exists
        Mono<Boolean> isExistMono = webClient.get()
                .uri(URLACCOUNT + "/account/isExist/{username}", accountDto.getUserName())
                .retrieve()
                .bodyToMono(Boolean.class).log();
        // If the account already exists, return an error signal
        Mono<AccountDTO> errorMono = isExistMono
                .filter(exists -> exists)
                .flatMap(exists -> Mono.error(new AccountException("Account already exists", HttpStatus.FOUND.value())));

        // If the account doesn't exist, create it
        Mono<AccountDTO> successMono = isExistMono
                .filter(exists -> !exists)
                .flatMap(exists -> {
                    accountDto.setPassword(encode.encode(accountDto.getPassword()));

                    // Create the account
                    Mono<AccountDTO> accountMono = webClient.post()
                            .uri(URLACCOUNT + "/account/add")
                            .body(Mono.just(accountDto), AccountDTO.class)
                            .retrieve()
                            .bodyToMono(AccountDTO.class);

                    // Create the security object
                    SecurityDTO security = new SecurityDTO();
                    security.setAccountId(accountDto.getUserName());
                    Mono<TransactionDTO> securityMono = webClient.post()
                            .uri(URLTRANSACTION + "/security/add")
                            .body(Mono.just(security), SecurityDTO.class)
                            .retrieve()
                            .bodyToMono(TransactionDTO.class);

                    // Create the balance object
                    BalanceDTO balanceDTO = new BalanceDTO();
                    balanceDTO.setAccountId(accountDto.getUserName());
                    Mono<BalanceDTO> balanceMono = webClient.post()
                            .uri(URLTRANSACTION + "/balance/add")
                            .body(Mono.just(balanceDTO), BalanceDTO.class)
                            .retrieve()
                            .bodyToMono(BalanceDTO.class);

                    // Create the login history object
                    LoginHistoryDTO loginHistory = new LoginHistoryDTO();
                    loginHistory.setAccountId(accountDto.getUserName());
                    Mono<LoginHistoryDTO> loginHistoryMono = webClient.post()
                            .uri(URLACCOUNT + "/loginHistory/add")
                            .body(Mono.just(loginHistory), LoginHistoryDTO.class)
                            .retrieve()
                            .bodyToMono(LoginHistoryDTO.class);

                    // Combine the results and return the account
                    return Mono.zip(accountMono, securityMono, balanceMono, loginHistoryMono)
                            .map(tuple -> tuple.getT1());
                });

        // Combine the success and error signals and handle errors gracefully
        return errorMono.switchIfEmpty(successMono)
                .onErrorResume(e -> {
                    log.error("Failed to create account", e);
                    return Mono.error(e);
                });
            }
//    public Mono<String> Login(LoginAccountDTO loginAccountDTO){
//        return webClient.post()
//                .uri(URLACCOUNT+"/account/login")
//
////                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
////                .
//                .body(Mono.just(loginAccountDTO), LoginAccountDTO.class)
//                .retrieve()
//                .bodyToMono(String.class)
//                .onErrorReturn("Login Failed");
//    }
    public Mono<String> isLogin(){
        return webClient.get()
                .uri(URLACCOUNT+"/account/islogin")
                .retrieve()
                .bodyToMono(String.class).log()
                .switchIfEmpty(Mono.just("user isn't login"));
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return webClient.get()
                .uri(URLACCOUNT+"/account/{userName}",username)
                .retrieve()
                .bodyToMono(AccountDTO.class)
                .map(accountDTO ->  User.builder()
                        .username(accountDTO.getUserName())
                        .password(accountDTO.getPassword())
                        .roles(accountDTO.getRole())
                        .build()
                );
    }
}
