package com.example.transporter.service;

import com.example.transporter.model.dto.AccountDTO;
import com.example.transporter.model.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(long id);

    Account findByEmail(String email);

    Account findByPhone(String phone);

    List<Account> findByRole(String role);

    List<Account> findByShipper();

    Account save(Account account);

    Account save(AccountDTO accountDTO);

    Account register(AccountDTO accountDTO);

    List<Account> findByStatusIsTrue();

}
