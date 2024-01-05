package com.example.transporter.repository;

import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.entity.CartItem;
import com.example.transporter.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartAndStatusIsTrue(Cart cart);

    CartItem findByCartAndProductAndStatusIsTrue(Cart cart, Product product);

}
