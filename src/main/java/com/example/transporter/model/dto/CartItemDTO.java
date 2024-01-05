package com.example.transporter.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {

    private long id;
    private int quantity;
    private String totalPrice;
    private double newPrice;
    private boolean status;

    private long productId;
    private ProductDTO productDTO;

    private long cartId;
    private CartDTO cartDTO;

}
