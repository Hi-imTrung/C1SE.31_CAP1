package com.example.transporter.handler;

import com.example.transporter.model.dto.CategoryDTO;
import com.example.transporter.model.dto.SearchDTO;
import com.example.transporter.model.mapper.CategoryMapper;
import com.example.transporter.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(annotations = Controller.class)
public class ModelHandler {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("categoryListDTO")
    public List<CategoryDTO> categoryListDTO() {
        return categoryMapper.toListDTO(categoryService.findAllByActive());
    }

    @ModelAttribute("searchDTO")
    public SearchDTO search() {
        return new SearchDTO();
    }
}
