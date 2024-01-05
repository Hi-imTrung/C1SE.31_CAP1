package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDTO {

    private long id;
    private String addressFrom;
    private String addressTo;
    private String progress;
    private String note;

    private long orderId;
    private OrderDTO orderDTO;

    private long shipperId;
    private AccountDTO shipperDTO;

}
