package com.example.Bank.Controller;

import com.example.Bank.Entity.LoginHistory;
import com.example.Bank.Service.ILoginHistoryService;
import com.example.Bank.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/loginHistory")
public class LoginHistoryController {
    @Autowired
    ILoginHistoryService loginService;

        @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<LoginHistory> addLoginHistory(@RequestBody LoginHistory loginHistory){
        return loginService.save(loginHistory);
    }

    @GetMapping("/{accountId}")
    public Mono<LoginHistory> getLoginHistory(@PathVariable("accountId") String accountId){
        return loginService.getLoginhistoryByAccountId(accountId);
    }
}
