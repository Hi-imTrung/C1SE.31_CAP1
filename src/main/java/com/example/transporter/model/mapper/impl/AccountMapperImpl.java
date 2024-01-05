package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.AccountDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.model.mapper.AccountMapper;
import com.example.transporter.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Autowired
    private AccountService accountService;

    @Override
    public AccountDTO toDTO(Account account) {
        if (account == null)
            return null;

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setPhone(account.getPhone());
        accountDTO.setAddress(account.getAddress());
        accountDTO.setRole(account.getRole());
        accountDTO.setStatus(account.isStatus());

        return accountDTO;
    }

    @Override
    public List<AccountDTO> toListDTO(List<Account> accountList) {
        if (accountList == null)
            return null;

        List<AccountDTO> result = new ArrayList<>();
        accountList.forEach(account -> {
            if (account != null) {
                result.add(toDTO(account));
            }
        });

        return result;
    }

    @Override
    public Account toEntity(AccountDTO accountDTO) {
        if (accountDTO == null)
            return null;

        Account account = accountService.findById(accountDTO.getId());
        if (account == null) {
            account = new Account();
        }

        account.setEmail(accountDTO.getEmail());
        account.setFullName(accountDTO.getFullName());
        account.setPhone(accountDTO.getPhone());
        account.setAddress(accountDTO.getAddress());
        account.setRole(accountDTO.getRole());
        account.setStatus(accountDTO.isStatus());

        return account;
    }
}
