package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.CategoryDTO;
import com.example.transporter.model.entity.Category;

import java.util.List;

public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    List<CategoryDTO> toListDTO(List<Category> categoryList);

    Category toEntity(CategoryDTO categoryDTO);
    
}
