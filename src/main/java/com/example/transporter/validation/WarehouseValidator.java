package com.example.transporter.validation;

import com.example.transporter.model.dto.WarehouseDTO;
import com.example.transporter.model.dto.WarehouseDTO;
import com.example.transporter.utils.ValidatorUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WarehouseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return WarehouseDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            WarehouseDTO warehouseDTO = (WarehouseDTO) target;

            if (ValidatorUtil.isEmpty(warehouseDTO.getTitle())) {
                errors.rejectValue("title", "Vui lòng nhập Tên Kho Hàng!",
                        "Vui lòng nhập Tên Kho Hàng!");
            }

            if (ValidatorUtil.isEmpty(warehouseDTO.getAddress())) {
                errors.rejectValue("address", "Vui lòng nhập Địa Chỉ!",
                        "Vui lòng nhập Địa Chỉ!");
            }

        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
