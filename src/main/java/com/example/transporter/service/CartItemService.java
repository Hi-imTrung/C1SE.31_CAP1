package com.example.transporter.service;

import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.entity.CartItem;
import com.example.transporter.model.entity.Product;

import java.util.List;

public interface CartItemService {

    CartItem findById(long id);

    List<CartItem> findByCart(Cart cart);

    CartItem findByCartAndProduct(Cart cart, Product product);

    CartItem save(CartItem cartItem);

}
