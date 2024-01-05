package com.example.transporter.validation;

import com.example.transporter.model.dto.AccountDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.service.AccountService;
import com.example.transporter.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChangeProfileValidator implements Validator {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            AccountDTO accountDTO = (AccountDTO) target;

            // verify fullName
            if (ValidatorUtil.isEmpty(accountDTO.getFullName())) {
                errors.rejectValue("fullName", "Vui lòng nhập Họ và Tên!",
                        "Vui lòng nhập Họ và Tên!");
            }

            // verify phone
            if (ValidatorUtil.isEmpty(accountDTO.getPhone())) {
                errors.rejectValue("phone", "Vui lòng nhập Số Điện Thoại!",
                        "Vui lòng nhập Số Điện Thoại!");
            } else {
                if (!ValidatorUtil.checkPhone(accountDTO.getPhone())) {
                    errors.rejectValue("phone", "Vui lòng nhập đúng định dạng", "Vui lòng nhập đúng định dạng");
                }
                else {
                    Account account = accountService.findByPhone(accountDTO.getPhone());
                    if (account != null && account.getPhone().equals(accountDTO.getPhone()) && account.getId() != accountDTO.getId()) {
                        errors.rejectValue("phone", "Số Điện Thoại đã được đăng ký!",
                                "Số Điện Thoại đã được đăng ký!");
                    }
                }
            }
        } catch(Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
