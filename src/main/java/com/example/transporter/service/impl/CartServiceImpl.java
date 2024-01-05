package com.example.transporter.service.impl;

import com.example.transporter.model.dto.CartDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.entity.Product;
import com.example.transporter.repository.CartRepository;
import com.example.transporter.service.AccountService;
import com.example.transporter.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public Cart findByAccount(Account account) {
        return cartRepository.findByAccount(account);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
