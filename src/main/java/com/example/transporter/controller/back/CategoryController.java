package com.example.transporter.controller.back;

import com.example.transporter.model.dto.CategoryDTO;
import com.example.transporter.model.dto.MessageDTO;
import com.example.transporter.model.entity.Category;
import com.example.transporter.model.mapper.CategoryMapper;
import com.example.transporter.service.CategoryService;
import com.example.transporter.validation.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/back/category")
public class CategoryController {

    private static final String REDIRECT_URL = "/back/category";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryValidator categoryValidator;

    @GetMapping(value = {"", "/"})
    public String list(Model model) {
        try {
            List<Category> categoryList = categoryService.findAll();

            model.addAttribute("categoryListDTO", categoryMapper.toListDTO(categoryList));
            return "back/category_list";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @GetMapping("/form")
    public String create(Model model) {
        try {
            model.addAttribute("categoryDTO", new CategoryDTO());
            return "back/category_form";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @GetMapping("/form/{id}")
    public String form(Model model, @PathVariable long id,
                       @RequestParam(required = false) String action,
                       @RequestParam(required = false) String status) {
        try {
            Category category = categoryService.findById(id);

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success")
                                ? "Cập nhật dữ liệu thành công!"
                                : "Vui lòng kiểm tra lại thông tin!"));
            }

            model.addAttribute("categoryDTO", categoryMapper.toDTO(category));
            return "back/category_form";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @PostMapping("/form")
    public String save(Model model, CategoryDTO categoryDTO, BindingResult bindingResult) {
        try {
            categoryValidator.validate(categoryDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("categoryDTO", categoryDTO);
                return "back/category_form";
            }
            Category category = categoryService.save(categoryMapper.toEntity(categoryDTO));

            String redirectUrl = "/back/category/form/"+ category.getId() + "?action=save&status=success";
            return "redirect:" + redirectUrl;
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }
}
