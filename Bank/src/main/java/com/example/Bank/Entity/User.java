package com.example.Bank.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("Customer")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    private String accountId;


    public User(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public User name(String name){
        this.name= name;
        return this;
    }
    public User email(String email){
        this.email= email;
        return this;
    }
    public User address(String address){
        this.address= address;
        return this;
    }
    public User build(){
        return new User(name,email,address);
    }
}
