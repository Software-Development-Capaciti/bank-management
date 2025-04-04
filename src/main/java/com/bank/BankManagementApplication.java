package com.bank;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(new Account("1234567890", "Savings", 1000.0));
            accountRepository.save(new Account("0987654321", "Checking", 500.0));
        };
    }
}