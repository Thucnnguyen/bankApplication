package com.example.BankBalance.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("Security")
public class Security {
    @Id
    private String accountId;
    private String securityCode;
    private String verificationCode;
    private LocalDateTime verificationCodeExpiration;
}
