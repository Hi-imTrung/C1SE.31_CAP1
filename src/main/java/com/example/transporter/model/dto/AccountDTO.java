package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private long id;
    private String role;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private boolean status;

    private String confirmPassword;
    private String oldPassword;
    private String newPassword;

    private CartDTO cartDTO;

}
