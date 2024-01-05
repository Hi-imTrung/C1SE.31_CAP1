package com.example.transporter.service.impl;

import com.example.transporter.model.entity.Category;
import com.example.transporter.model.entity.Product;
import com.example.transporter.repository.ProductRepository;
import com.example.transporter.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByRelated(long productId, long categoryId, int limit) {
        return productRepository.findByRelated(productId, categoryId, limit);
    }

    @Override
    public Page<Product> searchByTitleAndCategory(String search, long categoryId, Pageable pageable) {
        return productRepository.searchByTitleAndCategory(search, categoryId, pageable);
    }

    @Override
    public Page<Product> findByCategoryAndStatusTrue(Category category, Pageable pageable) {
        return category != null
                ? productRepository.findByCategoryAndStatusTrue(category, pageable)
                : productRepository.findByStatusTrue(pageable);
    }

    @Override
    public Page<Product> searchByStatusIsTrueAndTitleContains(String search, Pageable pageable) {
        return productRepository.searchByStatusIsTrueAndTitleContains(search, pageable);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByStatusIsTrue() {
        return productRepository.findByStatusIsTrue();
    }
}
