package com.example.BankApplication.Client;

import com.example.BankApplication.DTO.TransactionDTO;
import com.example.BankApplication.DTO.TransactionDetailDTO;
import com.example.BankApplication.Exception.BalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class TransactionClient {
    @Value("${restClient.balanceUrl}")
    private  String URLTRANSACTION;

    @Autowired
    WebClient webClient;

    public Mono<TransactionDTO> transfer(TransactionDTO transactionDto){
        Mono<Authentication> authentication= ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication);
        Mono<TransactionDTO> transactionWithDepositAccountId=authentication
                .map(Authentication::getName)
                .map(depositAccountId -> {
                    transactionDto.setDepositAccountId(depositAccountId);
                    return transactionDto;
                });

        return webClient.post()
                .uri(URLTRANSACTION+"/tran/transferMoney")
                .body(transactionWithDepositAccountId, TransactionDTO.class)
                .retrieve()
                .bodyToMono(TransactionDTO.class)
                .onErrorResume(error-> Mono.error(new BalanceException("Not enough money", HttpStatus.BAD_REQUEST.value())));

    }

    public Mono<String> getOTP(String accountId){
        return webClient.get()
                .uri(URLTRANSACTION+"/security/getOTP/{accountId}",accountId)
                .retrieve()
                .bodyToMono(String.class);
    }
    public Mono<TransactionDTO> verify(TransactionDetailDTO transactionDetailDTO){
        return webClient.post()
                .uri(URLTRANSACTION+"/tran/verify")
                .body(Mono.just(transactionDetailDTO),TransactionDetailDTO.class)
                .retrieve()
                .bodyToMono(TransactionDTO.class);
    }
}
