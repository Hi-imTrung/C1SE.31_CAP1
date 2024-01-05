package com.example.transporter.controller.front;

import com.example.transporter.model.dto.CartDTO;
import com.example.transporter.model.dto.CartItemDTO;
import com.example.transporter.model.entity.Product;
import com.example.transporter.model.mapper.ProductMapper;
import com.example.transporter.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product-detail")
public class ProductDetailController {

    private static final int PRODUCT_RELATED_LIMIT = 5;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{productId}")
    public String detail(Model model, @PathVariable long productId) {
        Product product = productService.findById(productId);
        List<Product> relatedListProduct = productService.findByRelated(productId, product.getCategory().getId(), PRODUCT_RELATED_LIMIT);

        model.addAttribute("productDTO", productMapper.toDTO(product));
        model.addAttribute("cartItemDTO", new CartItemDTO());
        model.addAttribute("relatedListProductDTO", productMapper.toListDTO(relatedListProduct));

        return "front/detail";
    }

}
