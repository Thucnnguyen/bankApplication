package com.example.BankBalance.Controller;

import com.example.BankBalance.Entity.Security;
import com.example.BankBalance.Service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    ISecurityService securityService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Security> addSecurityMono(@RequestBody Security security){
        return securityService.save(security);
    }

    @GetMapping("/getOTP/{accountId}")
    public Mono<String> getVerificationCode(@PathVariable("accountId")String id){
        return securityService.getVerificationCode(id);
    }

}
