package com.example.BankApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAccountDTO {
    @NotBlank(message = "username: is blank")
    private String userName;
    @NotBlank(message = "username: is password")
    private String password;
}
