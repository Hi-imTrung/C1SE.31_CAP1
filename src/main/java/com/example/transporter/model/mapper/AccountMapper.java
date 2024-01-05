package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.AccountDTO;
import com.example.transporter.model.entity.Account;

import java.util.List;

public interface AccountMapper {

    AccountDTO toDTO(Account account);

    List<AccountDTO> toListDTO(List<Account> accountList);

    Account toEntity(AccountDTO accountDTO);

}
