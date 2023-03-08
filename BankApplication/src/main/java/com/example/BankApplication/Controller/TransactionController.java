package com.example.BankApplication.Controller;

import com.example.BankApplication.Client.TransactionClient;
import com.example.BankApplication.DTO.TransactionDTO;
import com.example.BankApplication.DTO.TransactionDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tran")
public class TransactionController {
    @Autowired
    TransactionClient transactionClient;
    @PostMapping("/transfer")
    public Mono<TransactionDTO> transactionDtoMono(@RequestBody TransactionDTO transactionDto){
        return transactionClient.transfer(transactionDto);
    }

    @GetMapping("/getOTP/{accountId}")
    public Mono<String> getVerificationCode(@PathVariable("accountId")String id){
        return transactionClient.getOTP(id);
    }
    @PostMapping("/verify")
    public Mono<TransactionDTO> verify(@RequestBody TransactionDetailDTO transactionDetail){
        return transactionClient.verify(transactionDetail);
    }
}
