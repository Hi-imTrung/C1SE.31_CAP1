package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.CartItemDTO;
import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.entity.CartItem;
import com.example.transporter.model.entity.Product;
import com.example.transporter.model.mapper.CartItemMapper;
import com.example.transporter.model.mapper.ProductMapper;
import com.example.transporter.service.CartItemService;
import com.example.transporter.service.CartService;
import com.example.transporter.service.ProductService;
import com.example.transporter.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public CartItemDTO toDTO(CartItem cartItem) {
        if (cartItem == null)
            return null;
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setCartId(cartItem.getCart().getId());

        if (cartItem.getProduct() != null) {
            Product product = cartItem.getProduct();
            cartItemDTO.setProductDTO(productMapper.toDTO(product));
            cartItemDTO.setProductId(product.getId());

            double newPrice = (product.getPrice() - (product.getDiscount() * product.getPrice() / 100)) * cartItem.getQuantity();
            cartItemDTO.setNewPrice(newPrice);
            cartItemDTO.setTotalPrice(FormatUtils.formatNumber(newPrice));
        }

        return cartItemDTO;
    }

    @Override
    public List<CartItemDTO> toListDTO(List<CartItem> cartItemList) {
        if (cartItemList == null)
            return null;
        List<CartItemDTO> result = new ArrayList<>();

        cartItemList.forEach(cartItem -> {
            if (cartItem != null) {
                result.add(toDTO(cartItem));
            }
        });

        return result;
    }

    @Override
    public CartItem toEntity(CartItemDTO cartItemDTO) {
        if (cartItemDTO == null)
            return null;

        Cart cart = cartService.findById(cartItemDTO.getCartId());
        Product product = productService.findById(cartItemDTO.getProductId());

        CartItem cartItem = cartItemService.findByCartAndProduct(cart, product);
        if (cartItem != null) {
            // update quantity of product in cart
            int quantity = cartItem.getQuantity() + cartItemDTO.getQuantity();
            cartItem.setQuantity(quantity);
            return cartItem;
        }

        cartItem = new CartItem();
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setCart(cartService.findById(cartItemDTO.getCartId()));
        cartItem.setProduct(productService.findById(cartItemDTO.getProductId()));
        if (cartItem.getQuantity() == 0) {
            cartItem.setStatus(false);
        } else {
            cartItem.setStatus(cartItemDTO.isStatus());
        }

        return cartItem;
    }
}
