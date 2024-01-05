package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDTO {

    private long id;
    private String totalPrice;
    private int totalQuantity;
    private boolean status;

    private long accountId;
    private AccountDTO accountDTO;

    List<CartItemDTO> cartItemDTOList;
}
