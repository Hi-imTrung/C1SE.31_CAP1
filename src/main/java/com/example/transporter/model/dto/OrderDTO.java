package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private long id;
    private String fullName;
    private String phone;
    private String address;
    private String orderDate;
    private String totalAmount;
    private String progress;
    private String note;
    private String image;
    private boolean status;

    private long accountId;
    private AccountDTO accountDTO;

    private List<OrderItemDTO> orderItemDTOList;
    private MultipartFile imageMul;
}
