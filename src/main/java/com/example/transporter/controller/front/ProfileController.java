package com.example.transporter.controller.front;

import com.example.transporter.model.dto.AccountDTO;
import com.example.transporter.model.dto.MessageDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.model.entity.Order;
import com.example.transporter.model.mapper.AccountMapper;
import com.example.transporter.model.mapper.OrderItemMapper;
import com.example.transporter.model.mapper.OrderMapper;
import com.example.transporter.service.AccountService;
import com.example.transporter.service.CustomUserDetails;
import com.example.transporter.service.OrderItemService;
import com.example.transporter.service.OrderService;
import com.example.transporter.validation.ChangeProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ChangeProfileValidator changeProfileValidator;

    @GetMapping(value = {"", "/"})
    public String view(Model model, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();

        model.addAttribute("accountDTO", accountMapper.toDTO(account));
        return "front/profile";
    }

    @PostMapping("/change")
    public String changeProfile(Model model, Authentication authentication, AccountDTO accountDTO, BindingResult bindingResult) {
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Account account = customUserDetails.getAccount();

            changeProfileValidator.validate(accountDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("accountDTO", accountDTO);
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                return "front/profile";
            }
            account.setFullName(accountDTO.getFullName());
            account.setPhone(accountDTO.getPhone());
            account.setAddress(accountDTO.getAddress());
            accountService.save(account);

            return "redirect:/profile";
        } catch (Exception e) {
            return "front/profile";
        }
    }

    @GetMapping("/order")
    public String viewOrder(Model model, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();
        List<Order> orderByAccount = orderService.findByAccount(account);

        model.addAttribute("listOrderDTO", orderMapper.toListDTO(orderByAccount));
        return "front/profile_order";
    }

    @GetMapping("/order/{orderId}")
    public String viewOrderDetail(Model model, @PathVariable long orderId) {
        Order order = orderService.findById(orderId);
        model.addAttribute("orderDTO", orderMapper.toDTO(order));
        return "front/order_detail";
    }

    @GetMapping("/order/cancel/{orderId}")
    public String cancelOrder(Model model, @PathVariable long orderId) {
        Order order = orderService.findById(orderId);
        order.setProgress("CANCELED");
        orderService.save(order);
        model.addAttribute("orderDTO", orderMapper.toDTO(order));
        return "redirect:/profile/order";
    }

}
