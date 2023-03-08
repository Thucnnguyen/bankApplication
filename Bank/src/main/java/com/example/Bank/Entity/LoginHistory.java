package com.example.Bank.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("LoginHistory")
public class LoginHistory {
    @Id
    private String id;
    private LocalDateTime loginDate;
    private String accountId;
}
