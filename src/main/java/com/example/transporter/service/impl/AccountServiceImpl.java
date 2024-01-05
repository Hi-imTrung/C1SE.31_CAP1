package com.example.transporter.service.impl;

import com.example.transporter.model.dto.AccountDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.repository.AccountRepository;
import com.example.transporter.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Account findByPhone(String phone) {
        return accountRepository.findByPhone(phone);
    }

    @Override
    public List<Account> findByRole(String role) {
        return accountRepository.findByRole(role);
    }

    @Override
    public List<Account> findByShipper() {
        return accountRepository.findByRole("SHIPPER");
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account save(AccountDTO accountDTO) {
        Account account = findById(accountDTO.getId());

        if (account == null) {
            account = new Account();
            account.setStatus(true);
        }

        account.setFullName(accountDTO.getFullName());
        account.setEmail(accountDTO.getEmail());
        account.setPhone(accountDTO.getPhone());
        account.setRole(accountDTO.getRole());
        account.setStatus(accountDTO.isStatus());

        return accountRepository.save(account);
    }

    @Override
    public Account register(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }

        Account account = new Account();

        // role
        account.setRole("USER");

        // account
        account.setId(accountDTO.getId());
        account.setFullName(accountDTO.getFullName().trim());
        String encodedPassword = passwordEncoder.encode(accountDTO.getPassword());
        account.setPassword(encodedPassword);
        account.setPhone(accountDTO.getPhone());
        account.setEmail(accountDTO.getEmail().trim());
        account.setStatus(true);

        return accountRepository.save(account);
    }

    @Override
    public List<Account> findByStatusIsTrue() {
        return accountRepository.findByStatusIsTrue();
    }
}
