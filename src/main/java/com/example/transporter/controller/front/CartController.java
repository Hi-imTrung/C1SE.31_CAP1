package com.example.transporter.controller.front;

import com.example.transporter.model.dto.CartDTO;
import com.example.transporter.model.dto.CartItemDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Cart;
import com.example.transporter.model.entity.CartItem;
import com.example.transporter.model.entity.Product;
import com.example.transporter.model.mapper.CartItemMapper;
import com.example.transporter.model.mapper.CartMapper;
import com.example.transporter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @GetMapping(value = {"", "/"})
    public String viewCart(Model model, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();
        Cart cart = cartService.findByAccount(account);
        CartDTO cartDTO;
        if (cart == null) {
            cartDTO = new CartDTO();
            List<CartItemDTO> cartItemList = new ArrayList<>();
            cartDTO.setCartItemDTOList(cartItemList);
        } else {
            cartDTO = cartMapper.toDTO(cart);
        }

        model.addAttribute("cartDTO", cartDTO);
        return "front/cart";
    }

    @GetMapping("/add-to-cart/{productId}")
    public String addCart(Authentication authentication, @PathVariable long productId) {
        if (authentication == null) {
            return "redirect:/login";
        }
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setProductId(productId);
        cartItemDTO.setQuantity(1);

        return saveCart(authentication, cartItemDTO);
    }

    @GetMapping("/desc-to-cart/{productId}")
    public String descCart(Authentication authentication, @PathVariable long productId) {
        if (authentication == null) {
            return "redirect:/login";
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();
        Cart cart = cartService.findByAccount(account);

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setProductId(productId);
        cartItemDTO.setQuantity(-1);
        cartItemDTO.setCartId(cart.getId());
        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItem.setStatus(cartItem.getQuantity() != 0);
        cartItemService.save(cartItem);

        return "redirect:/cart";
    }

    @GetMapping("/remove-to-cart/{productId}")
    public String removeProduct(Authentication authentication, @PathVariable long productId) {
        Product product = productService.findById(productId);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();
        Cart cart = cartService.findByAccount(account);

        CartItem cartItem = cartItemService.findByCartAndProduct(cart, product);
        cartItem.setStatus(false);

        cartItemService.save(cartItem);
        return "redirect:/cart";
    }

    @PostMapping("")
    public String addToCart(Authentication authentication, CartItemDTO cartItemDTO) {
       return saveCart(authentication, cartItemDTO);
    }

    private String saveCart(Authentication authentication, CartItemDTO cartItemDTO) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();
        Cart cart = cartService.findByAccount(account);
        if (cart == null) {
            cart = new Cart();
            cart.setAccount(account);
            cart.setStatus(true);
            cart = cartService.save(cart);
        }
        cartItemDTO.setCartId(cart.getId());
        cartItemDTO.setStatus(true);
        CartItem cartItem = cartItemMapper.toEntity(cartItemDTO);
        cartItemService.save(cartItem);
        return "redirect:/cart";
    }

}
