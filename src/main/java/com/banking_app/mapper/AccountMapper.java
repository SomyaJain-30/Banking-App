package com.banking_app.mapper;

import com.banking_app.dto.AccountDto;
import com.banking_app.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
            accountDto.id(),
            accountDto.accountHolderName(),
            accountDto.balance()
        );
    }

    public static AccountDto mapTAccountDto(Account account){
        return new AccountDto(
            account.getId(),
            account.getAccountHolderName(),
            account.getBalance()
        );
    }
}
