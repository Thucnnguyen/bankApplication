package com.example.Bank.Entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("Account")
public class Account {

    @Id
    private String userName;
    private String password;
    @Indexed(unique = true)
    private String accountNumber;
    private String accountHolder;
    private String role;

    public Account userName(String userName){
        this.userName=userName;
        return this;
    }
    public Account role(String role){
        this.role=role;
        return this;
    }
    public Account password(String password){
        this.password=password;
        return this;
    }
    public Account accountNumber(String accountNumber){
        this.accountNumber=accountNumber;
        return this;
    }
    public Account accountHolder(String accountHolder){
        this.accountHolder=accountHolder;
        return this;
    }
//    public Account balance(double balance){
//        this.balance=balance;
//        return this;
//    }

    public Account build(){
        return new Account(userName,password,accountNumber,accountHolder,role);
    }
}
