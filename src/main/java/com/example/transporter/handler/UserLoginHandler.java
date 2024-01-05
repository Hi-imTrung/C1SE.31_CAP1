package com.example.transporter.handler;

import com.example.transporter.model.dto.AccountDTO;
import com.example.transporter.model.dto.CartDTO;
import com.example.transporter.model.dto.CartItemDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.mapper.CartMapper;
import com.example.transporter.service.CartService;
import com.example.transporter.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class UserLoginHandler {

    @Autowired
    private CartService cartService;


    @Autowired
    private CartMapper cartMapper;

    @ModelAttribute("userLogin")
    public AccountDTO getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = null;
        if (customUserDetails != null) {
            account = customUserDetails.getAccount();
        }
        AccountDTO accountDTO = null;
        if (account != null) {
            accountDTO = new AccountDTO();
            accountDTO.setFullName(account.getFullName());
            accountDTO.setEmail(account.getEmail());
            accountDTO.setRole(account.getRole());

            Cart cart = cartService.findByAccount(account);
            if (cart == null) {
                CartDTO cartDTO = new CartDTO();
                List<CartItemDTO> cartItemList = new ArrayList<>();
                cartDTO.setCartItemDTOList(cartItemList);
                accountDTO.setCartDTO(cartDTO);
            } else {
                accountDTO.setCartDTO(cartMapper.toDTO(cart));
            }
        }
        return accountDTO;
    }


}
