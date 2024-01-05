package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private long id;
    private String title;
    private String description;
    private boolean status;

    private long amountProduct;

}
