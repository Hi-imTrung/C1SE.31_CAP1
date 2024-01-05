package com.example.transporter.validation;

import com.example.transporter.model.dto.OrderDTO;
import com.example.transporter.utils.ValidatorUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CheckoutValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            OrderDTO orderDTO = (OrderDTO) target;
            if (ValidatorUtil.isEmpty(orderDTO.getFullName())) {
                errors.rejectValue("fullName", "Vui lòng nhập Họ & Tên!",
                        "Vui lòng nhập Họ & Tên!");
            }

            if (ValidatorUtil.isEmpty(orderDTO.getPhone())) {
                errors.rejectValue("phone", "Vui lòng nhập Số Điện Thoại!",
                        "Vui lòng nhập Số Điện Thoại!");
            }

            if (ValidatorUtil.isEmpty(orderDTO.getAddress())) {
                errors.rejectValue("address", "Vui lòng nhập Địa Chỉ!",
                        "Vui lòng nhập Địa Chỉ!");
            }

        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
