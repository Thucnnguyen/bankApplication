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
@Document("Transaction")
public class Transaction {
    @Id
    private String id;
    private Double amount;
    private LocalDateTime date;
    private String type;
    private String receiveAccountId;
    private String depositAccountId;

}
