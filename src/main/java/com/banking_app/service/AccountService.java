package com.banking_app.service;

import java.util.List;

import com.banking_app.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto account);

    AccountDto getAccountById(Long id);

    AccountDto depositAmount(Long id, Double amount);

    AccountDto withDrawAmount(Long id, Double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
}
