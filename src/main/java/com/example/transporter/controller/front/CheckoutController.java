package com.example.transporter.controller.front;

import com.example.transporter.model.dto.CartDTO;
import com.example.transporter.model.dto.FileDTO;
import com.example.transporter.model.dto.MessageDTO;
import com.example.transporter.model.dto.OrderDTO;
import com.example.transporter.model.entity.*;
import com.example.transporter.model.mapper.CartMapper;
import com.example.transporter.model.mapper.OrderMapper;
import com.example.transporter.service.*;
import com.example.transporter.utils.ConstantUtil;
import com.example.transporter.utils.DateUtil;
import com.example.transporter.utils.FormatUtils;
import com.example.transporter.utils.ValidatorUtil;
import com.example.transporter.validation.CheckoutValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CheckoutValidator checkoutValidator;

    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping(value = {"", "/"})
    public String viewCheckout(Model model, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();
        CartDTO cartDTO = cartMapper.toDTO(cartService.findByAccount(account));
        Order order = new Order();
        order.setAccount(account);
        order.setFullName(account.getFullName());
        order.setPhone(account.getPhone());
        order.setAddress(account.getAddress());
        order.setTotalAmount(FormatUtils.formatNumber(cartDTO.getTotalPrice()));

        model.addAttribute("cartDTO", cartDTO);
        model.addAttribute("orderDTO", orderMapper.toDTO(order));
        return "front/checkout";
    }

    @PostMapping("")
    public String checkout(Model model, Authentication authentication, OrderDTO orderDTO, BindingResult bindingResult) {
        try {
            checkoutValidator.validate(orderDTO, bindingResult);

            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = customUserDetails.getAccount();
            Cart cart = cartService.findByAccount(account);
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorList", ValidatorUtil.toErrors(bindingResult.getFieldErrors()));
                model.addAttribute("cartDTO", cartMapper.toDTO(cart));
                model.addAttribute("orderDTO", orderDTO);
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                return "front/checkout";
            }

            Date date = new Date();
            orderDTO.setAccountId(account.getId());
            orderDTO.setOrderDate(DateUtil.convertDateToString(date, ConstantUtil.DATE_HOUR_PATTERN));
            orderDTO.setProgress("PENDING");
            orderDTO.setStatus(true);
            Order order = orderMapper.toEntity(orderDTO);
            if (orderDTO.getImageMul() != null && !ObjectUtils.isEmpty(orderDTO.getImageMul().getOriginalFilename())) {
                FileDTO fileDTOBack = fileUploadService.uploadFile(orderDTO.getImageMul());
                order.setImage(fileDTOBack.getPath());
            }

            orderService.save(order);
            List<CartItem> cartItemList = cartItemService.findByCart(cart);
            cartItemList.forEach(cartItem -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setDiscount(cartItem.getProduct().getDiscount());
                orderItem.setPrice(cartItem.getProduct().getPrice());
                orderItemService.save(orderItem);

                cartItem.setStatus(false);
                cartItemService.save(cartItem);
            });

            return "redirect:/checkout/success";
        } catch (Exception exception) {
            return "404_error";
        }
    }

    @GetMapping("/success")
    public String checkoutSuccess() {
        return "front/checkout_success";
    }

}
