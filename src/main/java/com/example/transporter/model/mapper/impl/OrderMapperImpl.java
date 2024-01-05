package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.OrderDTO;
import com.example.transporter.model.dto.OrderItemDTO;
import com.example.transporter.model.entity.Order;
import com.example.transporter.model.mapper.AccountMapper;
import com.example.transporter.model.mapper.OrderItemMapper;
import com.example.transporter.model.mapper.OrderMapper;
import com.example.transporter.service.AccountService;
import com.example.transporter.service.OrderItemService;
import com.example.transporter.service.OrderService;
import com.example.transporter.utils.ConstantUtil;
import com.example.transporter.utils.DateUtil;
import com.example.transporter.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderDTO toDTO(Order order) {
        if (order == null)
            return null;

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setFullName(order.getFullName());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setPhone(order.getPhone());
        orderDTO.setTotalAmount(FormatUtils.formatNumber(order.getTotalAmount()));
        orderDTO.setProgress(order.getProgress());
        orderDTO.setNote(order.getNote());
        orderDTO.setImage(order.getImage());
        orderDTO.setStatus(order.isStatus());

        if (order.getAccount() != null) {
            orderDTO.setAccountId(order.getAccount().getId());
            orderDTO.setAccountDTO(accountMapper.toDTO(order.getAccount()));
        }

        if (order.getOrderDate() != null) {
            orderDTO.setOrderDate(DateUtil.convertDateToString(order.getOrderDate(), "dd-MM-yyyy"));
        }

        if (order.getId() != 0) {
            List<OrderItemDTO> orderItemListDTO = orderItemMapper.toListDTO(orderItemService.findByOrder(order));
            orderDTO.setOrderItemDTOList(orderItemListDTO);
        }

        return orderDTO;
    }

    @Override
    public List<OrderDTO> toListDTO(List<Order> orderList) {
        if (orderList == null)
            return null;

        List<OrderDTO> result = new ArrayList<>();
        orderList.forEach(order -> {
            if (order != null) {
                result.add(toDTO(order));
            }
        });

        return result;
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if (orderDTO == null)
            return null;

        Order order = orderService.findById(orderDTO.getId());
        if (order == null) {
            order = new Order();
        }
        order.setFullName(orderDTO.getFullName());
        order.setAddress(orderDTO.getAddress());
        order.setPhone(orderDTO.getPhone());
        order.setOrderDate(DateUtil.convertStringToDate(orderDTO.getOrderDate(), ConstantUtil.DATE_PATTERN));
        order.setTotalAmount(FormatUtils.formatNumber(orderDTO.getTotalAmount()));
        order.setProgress(orderDTO.getProgress());
        order.setNote(orderDTO.getNote());
        if (orderDTO.getImage() != null) {
            order.setImage(orderDTO.getImage());
        }
        order.setStatus(orderDTO.isStatus());
        order.setAccount(accountService.findById(orderDTO.getAccountId()));

        return order;
    }
}
