package com.example.transporter.controller.front;

import com.example.transporter.model.dto.AccountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping(value = {"/login"})
    public String showErrorLogin(Model model, @RequestParam String username) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(username);
        accountDTO.setPassword("");

        model.addAttribute("accountDTO", accountDTO);
        model.addAttribute("error", "error");

        return "login";
    }

}