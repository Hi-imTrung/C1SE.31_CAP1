package com.example.transporter.validation;

import com.example.transporter.model.dto.ProductDTO;
import com.example.transporter.utils.FormatUtils;
import com.example.transporter.utils.ValidatorUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            ProductDTO productDTO = (ProductDTO) target;

            if (ValidatorUtil.isEmpty(productDTO.getTitle())) {
                errors.rejectValue("title", "Vui lòng nhập Tên Sản Phẩm!",
                        "Vui lòng nhập Tên Sản Phẩm!");
            }

            if (ValidatorUtil.isEmpty(productDTO.getDescription())) {
                errors.rejectValue("description", "Vui lòng nhập Mô Tả!",
                        "Vui lòng nhập Mô Tả!");
            }

            if (productDTO.getCategoryId() == 0) {
                errors.rejectValue("categoryId", "Vui lòng nhập chọn Danh Mục!",
                        "Vui lòng nhập chọn Danh Mục!");
            }

            if (ValidatorUtil.isEmpty(productDTO.getDetail())) {
                errors.rejectValue("detail", "Vui lòng nhập Mô Tả Ngắn!",
                        "Vui lòng nhập Mô Tả Ngắn!");
            }

            String price = productDTO.getPrice();
            if (ValidatorUtil.isEmpty(price)) {
                errors.rejectValue("price", "Vui lòng nhập Giá Tiền!",
                        "Vui lòng nhập Giá Tiền!");
            } else {
                price = price.replaceAll(",", "").trim();
                if (!ValidatorUtil.isNumeric(price)) {
                    errors.rejectValue("price", "Giá Tiền phải là số!",
                            "Giá Tiền phải là số!");
                }
            }

            if (!ValidatorUtil.isNumeric(productDTO.getDiscount())) {
                errors.rejectValue("discount", "Giảm Giá phải là số!",
                        "Giảm Giá phải là số!");
            }

        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
