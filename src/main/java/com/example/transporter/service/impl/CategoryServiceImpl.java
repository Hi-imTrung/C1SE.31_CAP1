package com.example.transporter.service.impl;

import com.example.transporter.model.entity.Category;
import com.example.transporter.repository.CategoryRepository;
import com.example.transporter.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAllByActive() {
        return categoryRepository.findByStatusIsTrue();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findByStatusIsTrue() {
        return categoryRepository.findByStatusIsTrue();
    }
}
