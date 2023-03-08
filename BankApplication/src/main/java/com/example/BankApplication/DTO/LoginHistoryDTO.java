package com.example.BankApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginHistoryDTO {
    private String id;
    private LocalDateTime loginDate;
    private String accountId;
}
