package com.banking_app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banking_app.dto.AccountDto;
import com.banking_app.entity.Account;
import com.banking_app.exception.AccountException;
import com.banking_app.mapper.AccountMapper;
import com.banking_app.repository.AccountRepository;
import com.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{


    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapTAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account Doesn't Exists"));
        return AccountMapper.mapTAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account Doesn't Exists"));
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapTAccountDto(savedAccount);
    }

    @Override
    public AccountDto withDrawAmount(Long id, Double amount){
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account Doesn't Exists"));
        double currentBalance = account.getBalance();
        if(amount > currentBalance){
            throw new RuntimeException("Insufficient Balance");
        }
        double newBalance = currentBalance - amount;
        account.setBalance(newBalance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapTAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapTAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account doesn't exists"));
        accountRepository.deleteById(id);
    }
}
