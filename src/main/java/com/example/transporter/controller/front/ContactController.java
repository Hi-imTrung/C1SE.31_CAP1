package com.example.transporter.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @GetMapping(value = {"", "/"})
    public String contact() {
        return "front/contact";
    }

}
