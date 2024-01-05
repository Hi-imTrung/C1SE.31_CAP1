package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.OrderItemDTO;
import com.example.transporter.model.entity.OrderItem;
import com.example.transporter.model.entity.Product;
import com.example.transporter.model.mapper.OrderItemMapper;
import com.example.transporter.model.mapper.ProductMapper;
import com.example.transporter.service.OrderItemService;
import com.example.transporter.service.OrderService;
import com.example.transporter.service.ProductService;
import com.example.transporter.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public OrderItemDTO toDTO(OrderItem orderItem) {
        if (orderItem == null)
            return null;

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setOrderId(orderItem.getOrder().getId());
        orderItemDTO.setPrice(FormatUtils.formatNumber(orderItem.getPrice()));
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setDiscount(orderItem.getDiscount());

        orderItemDTO.setProductId(orderItem.getProduct().getId());

        Product product = orderItem.getProduct();
        orderItemDTO.setProductDTO(productMapper.toDTO(product));
        double newPrice = (product.getPrice() - (product.getDiscount() * product.getPrice() / 100)) * orderItem.getQuantity();
        orderItemDTO.setTotalPrice(FormatUtils.formatNumber(newPrice));

        return orderItemDTO;
    }

    @Override
    public List<OrderItemDTO> toListDTO(List<OrderItem> orderItemList) {
        if (orderItemList == null)
            return null;

        List<OrderItemDTO> result = new ArrayList<>();
        orderItemList.forEach(orderItem -> {
            if (orderItem != null) {
                result.add(toDTO(orderItem));
            }
        });

        return result;
    }

    @Override
    public OrderItem toEntity(OrderItemDTO orderItemDTO) {
        if (orderItemDTO == null)
            return null;

        OrderItem orderItem = orderItemService.findById(orderItemDTO.getId());
        if (orderItem == null) {
            orderItem = new OrderItem();
        }
        orderItem.setPrice(FormatUtils.formatNumber(orderItemDTO.getPrice()));
        orderItem.setDiscount(orderItemDTO.getDiscount());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setOrder(orderService.findById(orderItemDTO.getOrderId()));
        orderItem.setProduct(productService.findById(orderItemDTO.getProductId()));

        return orderItem;
    }
}
