package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.ShippingDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.Shipping;
import com.example.transporter.model.mapper.AccountMapper;
import com.example.transporter.model.mapper.OrderMapper;
import com.example.transporter.model.mapper.ShippingMapper;
import com.example.transporter.service.AccountService;
import com.example.transporter.service.OrderService;
import com.example.transporter.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShippingMapperImpl implements ShippingMapper {

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ShippingDTO toDTO(Shipping shipping) {
        if (shipping == null)
            return null;

        ShippingDTO shippingDTO = new ShippingDTO();
        shippingDTO.setId(shipping.getId());
        shippingDTO.setAddressFrom(shipping.getAddressFrom());
        shippingDTO.setAddressTo(shipping.getAddressTo());
        shippingDTO.setProgress(shipping.getProgress());
        shippingDTO.setNote(shipping.getNote());

        if (shipping.getShipper() != null) {
            Account shipper = shipping.getShipper();
            shippingDTO.setShipperId(shipper.getId());
            shippingDTO.setShipperDTO(accountMapper.toDTO(shipper));
        }

        if (shipping.getOrder() != null) {
            Order order = shipping.getOrder();
            shippingDTO.setOrderId(order.getId());
            shippingDTO.setOrderDTO(orderMapper.toDTO(order));
        }

        return shippingDTO;
    }

    @Override
    public List<ShippingDTO> toListDTO(List<Shipping> shippingList) {
        if (shippingList == null)
            return null;

        List<ShippingDTO> result = new ArrayList<>();
        shippingList.forEach(shipping -> {
            if (shipping != null) {
                result.add(toDTO(shipping));
            }
        });

        return result;
    }

    @Override
    public Shipping toEntity(ShippingDTO shippingDTO) {
        if (shippingDTO == null)
            return null;

        Shipping shipping = shippingService.findById(shippingDTO.getId());
        if (shipping == null) {
            shipping = new Shipping();
        }
        shipping.setAddressFrom(shippingDTO.getAddressFrom());
        shipping.setAddressTo(shippingDTO.getAddressTo());
        shipping.setProgress(shippingDTO.getProgress());
        shipping.setNote(shippingDTO.getNote());
        shipping.setOrder(orderService.findById(shippingDTO.getOrderId()));
        shipping.setShipper(accountService.findById(shippingDTO.getShipperId()));

        return shipping;
    }
}
