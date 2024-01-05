package com.example.transporter.controller.back;

import com.example.transporter.model.dto.EmailTemplateDTO;
import com.example.transporter.model.dto.MessageDTO;
import com.example.transporter.model.dto.ShippingDTO;
import com.example.transporter.model.entity.*;
import com.example.transporter.model.mapper.*;
import com.example.transporter.service.*;
import com.example.transporter.validation.ShippingValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/back/shipping")
public class ShippingController {

    private static final String REDIRECT_URL = "/back/shipping";

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ShippingValidation shippingValidation;

    @Autowired
    private MailService mailService;

    @GetMapping(value = {"", "/"})
    public String list(Model model, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Account account = customUserDetails.getAccount();
        List<Shipping> shippingList;

        if (account.getRole().equals("ADMIN")) {
            shippingList = shippingService.findAll();
        } else {
            shippingList = shippingService.findByAccount(account);
        }

        model.addAttribute("shippingListDTO", shippingMapper.toListDTO(shippingList));
        return "back/shipping_list";
    }

    @GetMapping("/form")
    public String editByShippingAndOrder(Model model,
                                         @RequestParam(value = "shippingId", required = false) long shippingId,
                                         @RequestParam(value = "orderId", required = false) long orderId,
                                         @RequestParam(required = false) String action,
                                         @RequestParam(required = false) String status) {
        try {
            Order order = orderService.findById(orderId);
            List<Shipping> shippingByOrder = shippingService.findByOrder(order);
            Shipping shipping = shippingService.findByIdAndOrder(shippingId, order);
            List<Account> accountList = accountService.findByShipper();
            List<Warehouse> warehouseList = warehouseService.findAll();
            Warehouse orderAddress = new Warehouse();
            orderAddress.setTitle(order.getAddress());
            orderAddress.setAddress(order.getAddress());
            warehouseList.add(0, orderAddress);

            if (shipping == null) {
                shipping = new Shipping();
                shipping.setOrder(order);
                if (!shippingByOrder.isEmpty()) {
                    Shipping lastShipping = shippingByOrder.get(shippingByOrder.size() - 1);
                    shipping.setAddressFrom(lastShipping.getAddressTo());
                }
            }

            Warehouse warehouse = warehouseService.findByAddress(shipping.getAddressFrom());
            if (warehouse != null) {
                model.addAttribute("warehouseDTO", warehouseMapper.toDTO(warehouse));
            } else {
                model.addAttribute("warehouseDTO", null);
            }

            model.addAttribute("shippingDTO", shippingMapper.toDTO(shipping));
            model.addAttribute("shipperListDTO", accountMapper.toListDTO(accountList));
            model.addAttribute("warehouseList", warehouseMapper.toListDTO(warehouseList));

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success")
                                ? "Cập nhật dữ liệu thành công!"
                                : "Vui lòng kiểm tra lại thông tin!"));
            }
            return "back/shipping_form";
        } catch (Exception ex) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @PostMapping("/form")
    public String save(Model model, ShippingDTO shippingDTO, BindingResult bindingResult) {
        try {
            // validation
            shippingValidation.validate(shippingDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                List<Account> accountList = accountService.findByShipper();
                List<Warehouse> warehouseList = warehouseService.findAll();
                Warehouse orderAddress = new Warehouse();
                orderAddress.setTitle(shippingDTO.getAddressTo());
                orderAddress.setAddress(shippingDTO.getAddressTo());
                warehouseList.add(0, orderAddress);

                Warehouse warehouse = warehouseService.findByAddress(shippingDTO.getAddressFrom());
                if (warehouse != null) {
                    model.addAttribute("warehouseDTO", warehouseMapper.toDTO(warehouse));
                } else {
                    model.addAttribute("warehouseDTO", null);
                }

                model.addAttribute("shippingDTO", shippingDTO);
                model.addAttribute("shipperListDTO", accountMapper.toListDTO(accountList));
                model.addAttribute("warehouseList", warehouseMapper.toListDTO(warehouseList));
                model.addAttribute("messageDTO", new MessageDTO("save", "warning",
                        "Vui lòng kiểm tra lại thông tin!"));
                return "back/shipping_form";
            }

            Order order = orderService.findById(shippingDTO.getOrderId());

            if (shippingDTO.getAddressTo().equals(order.getAddress())) {
                Map<String, Object> properties = new HashMap<>();
                properties.put("fullname", order.getFullName());
                properties.put("emailContact", "Email: info@transporter.vn");
                properties.put("order", orderMapper.toDTO(order));
                properties.put("phoneContact", "Phone: 0123-456-789");
                EmailTemplateDTO templateDTO = new EmailTemplateDTO();

                String progress = "";

                switch (shippingDTO.getProgress()) {
                    case "PENDING" -> progress += "đang lấy hàng";
                    case "APPROVED" -> progress += "đang giao hang";
                    case "COMPLETED" -> progress += "đã giao thành công";
                    default -> progress += "không thành công với lý do " + shippingDTO.getNote();
                }

                templateDTO.setContent("Đơn hàng " + progress);
                templateDTO.setSubject("Đơn Hàng- Transporter");
                String[] toEmail = {order.getAccount().getEmail()};
                templateDTO.setTo(toEmail);
                List<OrderItem> orderItems = orderItemService.findByOrder(order);
                properties.put("progress", progress);
                properties.put("orderItemDTOList", orderItemMapper.toListDTO(orderItems));
                templateDTO.setProperties(properties);

                mailService.sendEmailForOrder(templateDTO);
            }

            Shipping shipping = shippingMapper.toEntity(shippingDTO);
            shippingService.save(shipping);

            String redirectUrl = "/back/shipping/form?shippingId="+ shipping.getId()
                    + "&orderId=" + shippingDTO.getOrderId() + "&action=save&status=success";
            return "redirect:" + redirectUrl;
        } catch (Exception ex) {
            return "redirect:" + REDIRECT_URL;
        }
    }



}
