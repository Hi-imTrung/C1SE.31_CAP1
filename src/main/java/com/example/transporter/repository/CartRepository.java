package com.example.transporter.repository;

import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByAccount(Account account);

}
