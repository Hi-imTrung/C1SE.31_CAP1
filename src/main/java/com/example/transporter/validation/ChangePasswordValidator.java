package com.example.transporter.validation;

import com.example.transporter.model.dto.AccountDTO;
import com.example.transporter.model.entity.Account;
import com.example.transporter.service.AccountService;
import com.example.transporter.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChangePasswordValidator implements Validator {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            AccountDTO accountDTO = (AccountDTO) target;
            Account account;

            // verify oldPassword;
            if (ValidatorUtil.isEmpty(accountDTO.getOldPassword())) {
                errors.rejectValue("oldPassword", "Vui lòng nhập Mật Khẩu Cũ!",
                        "Vui lòng nhập Mật Khẩu Cũ!");
            } else {
                account = accountService.findById(accountDTO.getId());
                if (!passwordEncoder.matches(accountDTO.getOldPassword(), account.getPassword())) {
                    errors.rejectValue("oldPassword", "Mật Khẩu Cũ không chính xác!",
                            "Mật Khẩu Cũ không chính xác!");
                }
            }

            // verify newPassword
            if (ValidatorUtil.isEmpty(accountDTO.getNewPassword())) {
                errors.rejectValue("newPassword", "Vui lòng nhập Mật Khẩu Mới!",
                        "Vui lòng nhập Mật Khẩu Mới!");
            } else {
                if (accountDTO.getNewPassword().length() < 6) {
                    errors.rejectValue("newPassword", "Vui lòng nhập Mật Khẩu Mới lớn hơn 6 ký tự!",
                            "Vui lòng nhập Mật Khẩu Mới lớn hơn 6 ký tự!");
                } else if (accountDTO.getNewPassword().equals(accountDTO.getOldPassword())) {
                    errors.rejectValue("newPassword", "Mật Khẩu Mới bị trùng với Mật Khẩu Cũ!",
                            "Mật Khẩu Mới bị trùng với Mật Khẩu Cũ!");
                }
            }

            // verify confirmPassword
            if (ValidatorUtil.isEmpty(accountDTO.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "Vui lòng nhập Xác Nhận Mật Khẩu!",
                        "Vui lòng nhập Xác Nhận Mật Khẩu!");
            } else {
                if (accountDTO.getConfirmPassword().length() < 6) {
                    errors.rejectValue("confirmPassword", "Vui lòng nhập Xác Nhận Mật Khẩu lớn hơn 6 ký tự!",
                            "Vui lòng nhập Xác Nhận Mật Khẩu lớn hơn 6 ký tự!");
                } else if (!accountDTO.getConfirmPassword().equals(accountDTO.getNewPassword())) {
                    errors.rejectValue("confirmPassword", "Xác Nhận Mật Khẩu không trùng khớp với Mật Khẩu Mới!",
                            "Xác Nhận Mật Khẩu không trùng khớp với Mật Khẩu Mới!");
                }
            }
        } catch (Exception e) {
            errors.rejectValue("msg", "Có lỗi xảy ra, vui lòng thử lại!",
                    "Có lỗi xảy ra, vui lòng thử lại!");
        }
    }
}
