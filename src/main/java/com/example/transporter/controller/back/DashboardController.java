package com.example.transporter.controller.back;

import com.example.transporter.model.mapper.OrderMapper;
import com.example.transporter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back/dashboard")
public class DashboardController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @GetMapping(value = {"/", ""})
    public String viewDashboard(Model model) {
        model.addAttribute("totalAccount", accountService.findByStatusIsTrue().size());
        model.addAttribute("totalOrder", orderService.findByStatusIsTrue().size());
        model.addAttribute("totalProduct", productService.findByStatusIsTrue().size());
        model.addAttribute("totalCategory", categoryService.findByStatusIsTrue().size());

        model.addAttribute("orderList", orderMapper.toListDTO(orderService.findByProgress("SHIPPING")));
        model.addAttribute("orderListPending", orderMapper.toListDTO(orderService.findByProgress("PENDING")));

        return "back/dashboard";
    }

}
