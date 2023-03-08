package com.example.BankApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String accountId;


    public UserDTO(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }


    public UserDTO name(String name){
        this.name= name;
        return this;
    }
    public UserDTO email(String email){
        this.email= email;
        return this;
    }
    public UserDTO address(String address){
        this.address= address;
        return this;
    }
    public UserDTO build(){
        return new UserDTO(name,email,address);
    }
}
