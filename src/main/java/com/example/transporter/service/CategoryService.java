package com.example.transporter.service;

import com.example.transporter.model.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long id);

    List<Category> findAllByActive();

    Category save(Category category);

    List<Category> findByStatusIsTrue();

}
