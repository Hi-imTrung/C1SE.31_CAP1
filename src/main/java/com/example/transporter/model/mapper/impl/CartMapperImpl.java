package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.CartDTO;
import com.example.transporter.model.dto.CartItemDTO;
import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.mapper.AccountMapper;
import com.example.transporter.model.mapper.CartItemMapper;
import com.example.transporter.model.mapper.CartMapper;
import com.example.transporter.service.AccountService;
import com.example.transporter.service.CartItemService;
import com.example.transporter.service.CartService;
import com.example.transporter.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public CartDTO toDTO(Cart cart) {
        if (cart == null)
            return null;

        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setStatus(cart.isStatus());

        if (cart.getAccount() != null) {
            cartDTO.setAccountId(cart.getAccount().getId());
        }

        List<CartItemDTO> cartItemList = cartItemMapper.toListDTO(cartItemService.findByCart(cart));
        if (cartItemList == null) {
            cartItemList = new ArrayList<>();
            cartDTO.setCartItemDTOList(cartItemList);
        } else {
            cartDTO.setCartItemDTOList(cartItemList);
            double totalPrice = 0.0;
            int totalQuantity = 0;
            for (CartItemDTO cartItemDTO : cartItemList) {
                totalPrice += cartItemDTO.getNewPrice();
                totalQuantity += cartItemDTO.getQuantity();
            }
            cartDTO.setTotalPrice(FormatUtils.formatNumber(totalPrice));
            cartDTO.setTotalQuantity(totalQuantity);
        }

        return cartDTO;
    }

    @Override
    public List<CartDTO> toListDTO(List<Cart> cartList) {
        if (cartList == null)
            return null;

        List<CartDTO> result = new ArrayList<>();
        cartList.forEach(cart -> {
            if (cart != null) {
                result.add(toDTO(cart));
            }
        });

        return result;
    }

    @Override
    public Cart toEntity(CartDTO cartDTO) {
        if (cartDTO == null)
            return null;

        Cart cart = cartService.findById(cartDTO.getId());
        if (cart == null) {
            cart = new Cart();
        }
        cart.setStatus(cartDTO.isStatus());
        cart.setAccount(accountService.findById(cartDTO.getAccountId()));

        return cart;
    }
}
