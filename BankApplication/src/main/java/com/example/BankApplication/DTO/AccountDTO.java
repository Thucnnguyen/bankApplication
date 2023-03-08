package com.example.BankApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountDTO {
    @NotBlank(message = "Account.UserName: is blank")
    private String userName;
    @NotBlank(message = "Account.Password: is blank")

    private String password;
    @NotBlank(message = "Account.accountNumber: is blank")
    private String accountNumber;
    @NotBlank(message = "Account.accountHolder: is blank")
    private String accountHolder;
    @NotBlank(message = "Account.role: is blank")
    private String role;

    public AccountDTO userName(String userName){
        this.userName=userName;
        return this;
    }
    public AccountDTO role(String role){
        this.role=role;
        return this;
    }
    public AccountDTO password(String password){
        this.password=password;
        return this;
    }
    public AccountDTO accountNumber(String accountNumber){
        this.accountNumber=accountNumber;
        return this;
    }
    public AccountDTO accountHolder(String accountHolder){
        this.accountHolder=accountHolder;
        return this;
    }


    public AccountDTO build(){
        return new AccountDTO(userName,password,accountNumber,accountHolder,role);
    }
}
