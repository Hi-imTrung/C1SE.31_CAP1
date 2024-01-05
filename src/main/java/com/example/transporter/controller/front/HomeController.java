package com.example.transporter.controller.front;

import com.example.transporter.model.entity.Product;
import com.example.transporter.model.mapper.ProductMapper;
import com.example.transporter.service.ProductService;
import com.example.transporter.utils.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/home", ""})
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping(value = {"", "/"})
    public String viewHome(Model model) {
        // pagination
        Pageable pageable = PageRequest.of(0, 8, Sort.by("createdOn").descending());
        Page<Product> productList = productService.findByCategoryAndStatusTrue(null, pageable);
        List<Product> productListByPage = new ArrayList<>(productList.getContent());

        model.addAttribute("productListDTO", productMapper.toListDTO(productListByPage));

        return "front/home";
    }

}
