package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDTO {

    private long id;
    private String title;
    private String address;
    private String locationLat;
    private String locationLng;
    private boolean status;

}
