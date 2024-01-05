package com.example.transporter.service;

import com.example.transporter.model.entity.Category;
import com.example.transporter.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(long id);

    List<Product> findByCategory(Category category);

    List<Product> findByRelated(long productId, long categoryId, int limit);

    Page<Product> searchByTitleAndCategory(String search, long categoryId, Pageable pageable);

    Page<Product> findByCategoryAndStatusTrue(Category category, Pageable pageable);

    Page<Product> searchByStatusIsTrueAndTitleContains(String search, Pageable pageable);

    Product save(Product product);

    List<Product> findByStatusIsTrue();

}
