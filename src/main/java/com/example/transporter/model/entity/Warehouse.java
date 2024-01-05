package com.example.transporter.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class Warehouse extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "locationLng")
    private String locationLng;

    @Column(name = "locationLat")
    private String locationLat;

    @Column(name = "status")
    private boolean status;

}
