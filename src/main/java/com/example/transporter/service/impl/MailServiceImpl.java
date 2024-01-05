package com.example.transporter.service.impl;

import com.example.transporter.model.dto.EmailTemplateDTO;
import com.example.transporter.service.MailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    private static final String HOST_URL = "http://localhost:8080";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private Map<String, Object> properties = new HashMap<>();

    @Override
    public void sendEmailForOrder(EmailTemplateDTO emailDTO) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Context context = new Context();

            // set properties
            if (emailDTO.getProperties() != null && !emailDTO.getProperties().isEmpty()) {
                context.setVariables(emailDTO.getProperties());
            }

            String html = templateEngine.process("mail/order_complete.html", context);

            helper.setFrom(mailFrom);
            helper.setTo(emailDTO.getTo());
            helper.setSubject(emailDTO.getSubject());
            helper.setText(html, true);

            javaMailSender.send(message);
        } catch (Exception ex) {
        }
    }
}
