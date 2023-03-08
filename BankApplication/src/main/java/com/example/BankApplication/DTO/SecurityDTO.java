package com.example.BankApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SecurityDTO {
    private String id;
    private String securityCode;
    private String verificationCode;
    private LocalDateTime verificationCodeExpiration;
    private String accountId;
}
