package com.example.transporter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class EmailTemplateDTO {

    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String content;

    private Map<String, Object> properties;

    private String otp;
}
