package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductDTO {

    private long id;
    private String title;
    private String description;
    private String detail;
    private String image;
    private String price;
    private String discount;
    private String newPrice;
    private long categoryId;
    private boolean status;

    private MultipartFile imageMul;

}
