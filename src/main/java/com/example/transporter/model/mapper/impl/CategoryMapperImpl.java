package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.CategoryDTO;
import com.example.transporter.model.entity.Category;
import com.example.transporter.model.mapper.CategoryMapper;
import com.example.transporter.service.CategoryService;
import com.example.transporter.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Override
    public CategoryDTO toDTO(Category category) {
        if (category == null)
            return null;

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setStatus(category.isStatus());

        categoryDTO.setAmountProduct(productService.findByCategory(category).size());

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> toListDTO(List<Category> categoryList) {
        if (categoryList == null)
            return null;

        List<CategoryDTO> result = new ArrayList<>();
        categoryList.forEach(category -> {
            if (category != null) {
                result.add(toDTO(category));
            }
        });

        return result;
    }

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null)
            return null;

        Category category = categoryService.findById(categoryDTO.getId());
        if (category == null) {
            category = new Category();
        }
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(categoryDTO.isStatus());

        return category;
    }
}
