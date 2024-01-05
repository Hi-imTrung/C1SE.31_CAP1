package com.example.transporter.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "detail")
    private String detail;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;

    @Column(name = "status")
    private boolean status;

}
