package com.example.BankBalance.Schedule;

import com.example.BankBalance.Repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class securityScheduler {
    @Autowired
    SecurityRepository securityRepository;

    @Scheduled(fixedRate = 120000)
    public void resetVerificationCodes(){
            securityRepository.findByVerificationCodeExpirationBefore(LocalDateTime.now().minusMinutes(1)).log()
                    .flatMap(security -> {
                        security.setVerificationCode("");
                        security.setVerificationCodeExpiration(null);
                        return securityRepository.save(security);
                    })
                .subscribe();

    }

}
