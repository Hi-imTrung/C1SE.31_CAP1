package com.example.transporter.repository;

import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    Shipping findByIdAndOrder(long id, Order order);

    List<Shipping> findByOrder(Order order);

    List<Shipping> findByShipper(Account account);

}
