package com.example.BankBalance.Service;

import com.example.BankBalance.Entity.Security;
import com.example.BankBalance.Repository.SecurityRepository;
import com.example.BankBalance.Ultils.createVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class SecurityService implements ISecurityService{
    @Autowired
     SecurityRepository securityRepository;

    createVerificationCode createVerificationCode= new createVerificationCode();

    @Override
    public Mono<Security> save(Security security) {
        return securityRepository.save(security);
    }

    @Override
    public Mono<String> getVerificationCode(String id) {
        return securityRepository.findByAccountId(id)
                .map(s-> s.getVerificationCode());
    }

    @Override
    public String verificationCode(String userName) {
        String verificationCode=createVerificationCode.createVerificationCode(5);

        securityRepository.findByAccountId(userName)
                .subscribe(s -> {
                    s.setVerificationCode(verificationCode);
                    securityRepository.save(s);
                });
        return verificationCode;
    }

    @Override
    public Mono<Security> update(Security security) {
        return securityRepository.save(security);
    }

    public Flux<Security> findSecurityFlux(){
        return securityRepository.findByVerificationCodeExpirationBefore(LocalDateTime.now());
    }
}
