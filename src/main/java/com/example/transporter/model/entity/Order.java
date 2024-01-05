package com.example.transporter.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "progress")
    private String progress;

    @Column(name = "note")
    private String note;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private boolean status;

}
