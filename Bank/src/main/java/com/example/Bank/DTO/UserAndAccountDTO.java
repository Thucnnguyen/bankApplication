package com.example.Bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAndAccountDTO implements Serializable {
    private String name;
    private String email;
    private String address;
    private String userName;
    private String password;
    private String accountNumber;
    private String accountHolder;
    private String role;
}
