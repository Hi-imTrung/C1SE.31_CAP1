package com.example.transporter.model.mapper.impl;

import com.example.transporter.model.dto.ProductDTO;
import com.example.transporter.model.entity.Product;
import com.example.transporter.model.mapper.ProductMapper;
import com.example.transporter.service.CategoryService;
import com.example.transporter.service.ProductService;
import com.example.transporter.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public ProductDTO toDTO(Product product) {
        if (product == null)
            return null;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setDetail(product.getDetail());
        productDTO.setPrice(FormatUtils.formatNumber(product.getPrice()));
        productDTO.setDiscount(FormatUtils.formatNumber(product.getDiscount()));
        productDTO.setImage(product.getImage());
        productDTO.setStatus(product.isStatus());

        if (product.getDiscount() != 0.0) {
            double newPrice = product.getPrice() - (product.getPrice() * product.getDiscount() / 100);
            productDTO.setNewPrice(FormatUtils.formatNumber(newPrice));
        }
        if (!ObjectUtils.isEmpty(product.getCategory())) {
            productDTO.setCategoryId(product.getCategory().getId());
        }

        return productDTO;
    }

    @Override
    public List<ProductDTO> toListDTO(List<Product> productList) {
        if (productList == null)
            return null;

        List<ProductDTO> result = new ArrayList<>();
        productList.forEach(product -> {
            if (product != null) {
                result.add(toDTO(product));
            }
        });

        return result;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        if (productDTO == null)
            return null;

        Product product = productService.findById(productDTO.getId());
        if (product == null) {
            product = new Product();
        }
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setDetail(productDTO.getDetail());
        product.setPrice(FormatUtils.formatNumber(productDTO.getPrice()));
        product.setDiscount(FormatUtils.formatNumber(productDTO.getDiscount()));
        product.setStatus(productDTO.isStatus());
        product.setCategory(categoryService.findById(productDTO.getCategoryId()));
        product.setImage(productDTO.getImage());

        return product;
    }
}
