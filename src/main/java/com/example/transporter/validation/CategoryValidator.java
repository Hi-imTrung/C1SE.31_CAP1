package com.example.transporter.validation;

import com.example.transporter.model.dto.CategoryDTO;
import com.example.transporter.model.dto.ShippingDTO;
import com.example.transporter.utils.ValidatorUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            CategoryDTO categoryDTO = (CategoryDTO) target;

            if (ValidatorUtil.isEmpty(categoryDTO.getTitle())) {
                errors.rejectValue("title", "Vui lòng nhập Tiêu Đề!",
                        "Vui lòng nhập Tiêu Đề!");
            }

            if (ValidatorUtil.isEmpty(categoryDTO.getDescription())) {
                errors.rejectValue("description", "Vui lòng nhập Mô Tả!",
                        "Vui lòng nhập Mô Tả!");
            }

        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
