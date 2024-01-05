package com.example.transporter.controller.back;

import com.example.transporter.model.dto.MessageDTO;
import com.example.transporter.model.dto.OrderDTO;
import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.OrderItem;
import com.example.transporter.model.entity.Shipping;
import com.example.transporter.model.entity.Warehouse;
import com.example.transporter.model.mapper.OrderItemMapper;
import com.example.transporter.model.mapper.OrderMapper;
import com.example.transporter.model.mapper.ShippingMapper;
import com.example.transporter.model.mapper.WarehouseMapper;
import com.example.transporter.service.OrderItemService;
import com.example.transporter.service.OrderService;
import com.example.transporter.service.ShippingService;
import com.example.transporter.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/back/order")
public class OrderController {

    private static final String REDIRECT_URL = "/back/order";

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @GetMapping(value = {"", "/"})
    public String list(Model model) {
        try {
            List<Order> orderList = orderService.findAll();

            model.addAttribute("orderListDTO", orderMapper.toListDTO(orderList));
            return "back/order_list";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @GetMapping("/form/{orderId}")
    public String edit(Model model, @PathVariable long orderId,
                       @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Order order = orderService.findById(orderId);
            List<OrderItem> orderItemList = orderItemService.findByOrder(order);
            List<Shipping> shippingList = shippingService.findByOrder(order);

            if (!shippingList.isEmpty()) {
                Shipping lastShipping = shippingList.get(shippingList.size() - 1);
                model.addAttribute("progress", lastShipping.getProgress().equals("COMPLETED"));

                List<Warehouse> warehouseList = getWarehouse(shippingList);
                model.addAttribute("warehouseDTOList", warehouseMapper.toListDTO(warehouseList));
            } else {
                model.addAttribute("progress", true);
            }

            model.addAttribute("orderDTO", orderMapper.toDTO(order));
            model.addAttribute("orderItemListDTO", orderItemMapper.toListDTO(orderItemList));
            model.addAttribute("shippingListDTO", shippingMapper.toListDTO(shippingList));

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success")
                                ? "Cập nhật dữ liệu thành công!"
                                : "Vui lòng kiểm tra lại thông tin!"));
            }

            return "back/order_form";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @PostMapping("/form")
    public String save(Model model, OrderDTO orderDTO) {
        try {
            orderService.save(orderMapper.toEntity(orderDTO));
            String redirectUrl = "/back/order/form/" + orderDTO.getId() + "?action=save&status=success";
            return "redirect:" + redirectUrl;
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    private List<Warehouse> getWarehouse(List<Shipping> shippingList) {
        List<Warehouse> result = new ArrayList<>();

        for (int index = 0; index < shippingList.size(); index++) {
            Warehouse warehouse = warehouseService.findByAddress(shippingList.get(index).getAddressFrom());;
            result.add(warehouse);
            if (index == shippingList.size() - 1) {
                Warehouse lastWarehouse = warehouseService.findByAddress(shippingList.get(index).getAddressTo());
                if (lastWarehouse == null) {
                    lastWarehouse = new Warehouse();
                    lastWarehouse.setLocationLng("0");
                    lastWarehouse.setLocationLat("0");
                    lastWarehouse.setAddress(shippingList.get(index).getAddressTo());
                    result.add(lastWarehouse);
                }
            }
        }

        return result;
    }

}
