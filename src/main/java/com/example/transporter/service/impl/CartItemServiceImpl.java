package com.example.transporter.service.impl;

import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.entity.CartItem;
import com.example.transporter.model.entity.Product;
import com.example.transporter.repository.CartItemRepository;
import com.example.transporter.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem findById(long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCartAndStatusIsTrue(cart);
    }

    @Override
    public CartItem findByCartAndProduct(Cart cart, Product product) {
        return cartItemRepository.findByCartAndProductAndStatusIsTrue(cart, product);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
