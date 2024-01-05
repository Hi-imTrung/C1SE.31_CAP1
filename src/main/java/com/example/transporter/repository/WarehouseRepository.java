package com.example.transporter.repository;

import com.example.transporter.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Warehouse findByAddress(String address);

}
