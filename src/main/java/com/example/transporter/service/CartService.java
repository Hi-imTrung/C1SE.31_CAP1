package com.example.transporter.service;

import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.entity.Product;

import java.util.List;

public interface CartService {

    List<Cart> findAll();

    Cart findById(long id);

    Cart findByAccount(Account account);

    Cart save(Cart cart);

}
