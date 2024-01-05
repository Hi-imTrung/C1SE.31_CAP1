package com.example.transporter.controller.back;

import com.example.transporter.model.dto.MessageDTO;
import com.example.transporter.model.dto.WarehouseDTO;
import com.example.transporter.model.entity.Warehouse;
import com.example.transporter.model.mapper.WarehouseMapper;
import com.example.transporter.service.WarehouseService;
import com.example.transporter.validation.WarehouseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/back/warehouse")
public class WarehouseController {

    private static final String REDIRECT_URL = "/back/warehouse";

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private WarehouseValidator warehouseValidator;

    @GetMapping(value = {"", "/"})
    public String list(Model model) {
        List<Warehouse> warehouseList = warehouseService.findAll();

        model.addAttribute("warehouseListDTO", warehouseMapper.toListDTO(warehouseList));
        return "back/warehouse_list";
    }

    @GetMapping("/form/{id}")
    public String detail(Model model, @PathVariable long id,
                         @RequestParam(required = false) String action,
                         @RequestParam(required = false) String status) {
        try {
            Warehouse warehouse = warehouseService.findById(id);
            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success")
                                ? "Cập nhật dữ liệu thành công!"
                                : "Vui lòng kiểm tra lại thông tin!"));
            }

            model.addAttribute("warehouseDTO", warehouseMapper.toDTO(warehouse));
            return "back/warehouse_form";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @GetMapping("/form")
    public String create(Model model) {
        model.addAttribute("warehouseDTO", new WarehouseDTO());
        return "back/warehouse_form";
    }

    @PostMapping("/form")
    public String save(Model model, WarehouseDTO warehouseDTO, BindingResult bindingResult) {
        try {
            warehouseValidator.validate(warehouseDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("warehouseDTO", warehouseDTO);
                return "back/warehouse_form";
            }

            Warehouse warehouse = warehouseMapper.toEntity(warehouseDTO);
            warehouseService.save(warehouse);

            String redirectUrl = "/back/warehouse/form/" + warehouse.getId() + "?action=save&status=success";
            return "redirect:" + redirectUrl;
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

}
