package com.example.BankApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAndAccountDTO  {
    @NotBlank(message = "User.name: is blank")
    private String name;

    private String email;
    private String address;
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
}
