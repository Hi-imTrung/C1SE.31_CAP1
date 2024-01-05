package com.example.transporter.validation;

import com.example.transporter.model.dto.ShippingDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ShippingValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ShippingDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            ShippingDTO shippingDTO = (ShippingDTO) target;

            String addressFrom = shippingDTO.getAddressFrom();
            if (addressFrom.equalsIgnoreCase("default")) {
                errors.rejectValue("addressFrom", "Vui lòng chọn Nơi Đi!",
                        "Vui lòng chọn Nơi Đi!");
            } else {
                if (addressFrom.equalsIgnoreCase(shippingDTO.getAddressTo())) {
                    errors.rejectValue("addressFrom", "Nơi Đi không thể trùng với Nơi Đến!",
                            "Nơi Đi không thể trùng với Nơi Đến!");
                }
            }

            if (shippingDTO.getShipperId() == 0) {
                errors.rejectValue("shipperId", "Vui lòng chọn Người Giao Hàng!",
                        "Vui lòng chọn Người Giao Hàng!");
            }

        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
