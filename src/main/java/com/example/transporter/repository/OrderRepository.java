package com.example.transporter.repository;

import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByAccountOrderByOrderDateDesc(Account account);

    List<Order> findByProgress(String progress);

    List<Order> findByStatusIsTrue();

    @Query(value = "SELECT * FROM orders WHERE order_Date BETWEEN :startDate AND :endDate order by order_Date DESC", nativeQuery = true)
    List<Order> findReport(String startDate, String endDate);
}
