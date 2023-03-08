package com.example.BankBalance.Service;


import com.example.BankBalance.Entity.Transaction;
import com.example.BankBalance.Repository.SecurityRepository;
import com.example.BankBalance.Repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    SecurityRepository securityRepository;
    @Autowired
    SecurityService securityService;
    @Autowired
    IBalanceService balanceService;
    @Override
    public Mono<Transaction> getTransactionById(String id) {

        return transactionRepository.existsById(id)
                .flatMap(b -> {
                    if (!b) {
                        return Mono.error(new RuntimeException("Transaction not exist!!"));
                    }
                    return transactionRepository.findById(id);
                });
    }

    @Override
    public Mono<Transaction> transfer(Transaction transaction) {
        String verificationCode = securityService.verificationCode(transaction.getDepositAccountId());
        return balanceService.findByAccountId(transaction.getDepositAccountId())
                .filter(account -> account.getBalance()>= transaction.getAmount())
                .switchIfEmpty(Mono.error(new RuntimeException("amount not enough")))
                .flatMap(balance -> {
                    return securityRepository.findByAccountId(balance.getAccountId());
                })
                .flatMap(s -> {
                    s.setVerificationCode(verificationCode);
                    s.setVerificationCodeExpiration(LocalDateTime.now());
                    return securityRepository.save(s);
                })
                .then(transactionRepository.save(transaction));

    }

    @Override
    public Mono<Transaction> deleteTransfer(String id) {
        return transactionRepository.findById(id).flatMap(
                r -> {
                    r.setType("Delete");
                    return transactionRepository.save(r);
                }
        );
    }

    @Override
    public Mono<Transaction> verifyTransaction(String verificationCode, String transactionId) {
        
        return transactionRepository.findById(transactionId)
                .flatMap(transaction -> securityRepository.findByAccountId(transaction.getDepositAccountId())
                        .filter(s -> s.getVerificationCode().equals(verificationCode))
                        .switchIfEmpty(Mono.error(new RuntimeException("Verification failed")))
                        .thenReturn(transaction))
                .flatMap(t -> {
                    Mono<Void> reciveAccount=balanceService.findByAccountId(t.getReceiveAccountId())
                            .flatMap(tr -> {
                                tr.setBalance(tr.getBalance() + t.getAmount());
                                return balanceService.save(tr).then();
                            });
                    Mono<Void> depositoryAccount =balanceService.findByAccountId(t.getDepositAccountId())
                            .flatMap(tr -> {
                                tr.setBalance(tr.getBalance() - t.getAmount());
                                return balanceService.save(tr).then();
                            });
                    t.setType("success");
                    return reciveAccount.and(depositoryAccount).then(transactionRepository.save(t));
                });
    }

    @Override
    public Flux<Transaction> getTransactionByAccountId(String accountId) {
        return transactionRepository.findByReceiveAccountIdOrDepositAccountId(accountId);
    }

    @Override
    public Mono<Transaction> updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
