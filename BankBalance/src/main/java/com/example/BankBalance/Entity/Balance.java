package com.example.BankBalance.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Balance")
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    @Id
    private String accountId;
    private double Balance;
}
