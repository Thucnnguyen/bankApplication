package com.example.BankBalance.Controller;

import com.example.BankBalance.Entity.Balance;
import com.example.BankBalance.Service.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    IBalanceService balanceService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Balance> addBalance(@RequestBody Balance balance){
            return balanceService.save(balance);
    }


}
