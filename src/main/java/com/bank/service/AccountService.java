package com.bank.service;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account deposit(Long id, double amount) throws Exception {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new Exception("Account not found"));
        
        if (amount <= 0) {
            throw new Exception("Deposit amount must be positive");
        }
        
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdraw(Long id, double amount) throws Exception {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new Exception("Account not found"));
        
        if (amount <= 0) {
            throw new Exception("Withdrawal amount must be positive");
        }
        if (amount > account.getBalance()) {
            throw new Exception("Insufficient funds");
        }
        
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    public Account createAccount(String accountNumber, String type, double initialBalance) throws Exception {
        if (initialBalance < 0) {
            throw new Exception("Initial balance cannot be negative");
        }
        Account account = new Account(accountNumber, type, initialBalance);
        return accountRepository.save(account);
    }
}