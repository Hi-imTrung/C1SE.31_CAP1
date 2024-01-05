package com.example.transporter.model.mapper;

import com.example.transporter.model.dto.ProductDTO;
import com.example.transporter.model.entity.Product;

import java.util.List;

public interface ProductMapper {

    ProductDTO toDTO(Product product);

    List<ProductDTO> toListDTO(List<Product> productList);

    Product toEntity(ProductDTO productDTO);
    
}
