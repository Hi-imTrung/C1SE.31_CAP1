package com.example.transporter.repository;

import com.example.transporter.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

    Account findByPhone(String phone);

    List<Account> findByRole(String role);

    List<Account> findByStatusIsTrue();

}
