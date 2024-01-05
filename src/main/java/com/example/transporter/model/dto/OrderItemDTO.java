package com.example.transporter.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

    private long id;
    private String price;
    private double discount;
    private int quantity;
    private String totalPrice;

    private long orderId;
    private OrderDTO orderDTO;

    private long productId;
    private ProductDTO productDTO;

}
