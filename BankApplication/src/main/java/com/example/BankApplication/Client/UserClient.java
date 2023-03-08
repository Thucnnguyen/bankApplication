package com.example.BankApplication.Client;

import com.example.BankApplication.DTO.AccountDTO;
import com.example.BankApplication.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClient {
    @Value("${restClient.accountUrl}")
    private String URLACCOUNT;
    @Autowired
    AccountClient accountClient;
    @Autowired
    WebClient webClient;

    public Mono<UserDTO> addUserAndAccount(UserDTO user, AccountDTO account) {
        return Mono.defer(()->{
            Mono<AccountDTO> accountMono= accountClient.addAccount(account);
            return accountMono.zipWith(Mono.just(user));
        }).flatMap(tuple->{
            AccountDTO a= tuple.getT1();
            UserDTO u= tuple.getT2();
            u.setAccountId(a.getUserName());
            return webClient.post()
                    .uri(URLACCOUNT+"/user/add")
                    .body(Mono.just(user), UserDTO.class)
                    .retrieve()
                    .bodyToMono(UserDTO.class);
        });
    }
}
