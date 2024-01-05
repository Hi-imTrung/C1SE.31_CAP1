package com.example.transporter.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipping")
public class Shipping extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "shipper_id")
    private Account shipper;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "address_from")
    private String addressFrom;

    @Column(name = "address_to")
    private String addressTo;

    @Column(name = "progress")
    private String progress;

    @Column(name = "note")
    private String note;

}
