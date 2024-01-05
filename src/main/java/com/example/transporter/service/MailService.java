package com.example.transporter.service;

import com.example.transporter.model.dto.EmailTemplateDTO;

public interface MailService {

    void sendEmailForOrder(EmailTemplateDTO emailDTO);

}
