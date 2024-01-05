package com.example.transporter.repository;

import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);

}
