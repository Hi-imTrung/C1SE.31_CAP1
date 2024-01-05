package com.example.transporter.repository;

import com.example.transporter.model.entity.Category;
import com.example.transporter.model.entity.Order;
import com.example.transporter.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);

    @Query(value = """
            SELECT * FROM product p\s
            WHERE p.id != :productId AND p.category_id = :categoryId AND p.status = TRUE\s
            ORDER BY RAND() LIMIT :limit""", nativeQuery = true)
    List<Product> findByRelated(long productId, long categoryId, int limit);

    @Query(value = """
            SELECT * FROM product p\s
            WHERE p.title LIKE %?1% AND p.category_id = ?2 AND p.status = TRUE""", nativeQuery = true)
    Page<Product> searchByTitleAndCategory(String title, long categoryId, Pageable pageable);

    Page<Product> findByStatusTrue(Pageable pageable);

    Page<Product> findByCategoryAndStatusTrue(Category category, Pageable pageable);

    List<Product> findByStatusIsTrue();

    Page<Product> searchByStatusIsTrueAndTitleContains(String search, Pageable pageable);

}
