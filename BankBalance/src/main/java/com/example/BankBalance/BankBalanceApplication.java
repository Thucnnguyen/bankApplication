package com.example.BankBalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankBalanceApplication.class, args);
	}

}
