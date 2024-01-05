package com.example.transporter.controller.back;

import com.example.transporter.model.dto.FileDTO;
import com.example.transporter.model.dto.MessageDTO;
import com.example.transporter.model.dto.ProductDTO;
import com.example.transporter.model.entity.Category;
import com.example.transporter.model.entity.Product;
import com.example.transporter.model.mapper.CategoryMapper;
import com.example.transporter.model.mapper.ProductMapper;
import com.example.transporter.service.CategoryService;
import com.example.transporter.service.FileUploadService;
import com.example.transporter.service.ProductService;
import com.example.transporter.validation.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/back/product")
public class ProductController {

    private static final String REDIRECT_URL = "/back/product";

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductValidator productValidator;

    @GetMapping(value = {"", "/"})
    public String list(Model model) {
        try {
            List<Product> productList = productService.findAll();

            model.addAttribute("productListDTO", productMapper.toListDTO(productList));
            return "back/product_list";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @GetMapping("/form/{productId}")
    public String detail(Model model, @PathVariable long productId,
                         @RequestParam(required = false) String action,
                         @RequestParam(required = false) String status) {
        try {
            List<Category> categoryList = categoryService.findAll();
            Product product = productService.findById(productId);

            model.addAttribute("productDTO", productMapper.toDTO(product));
            model.addAttribute("categoryList", categoryMapper.toListDTO(categoryList));

            if (action == null) {
                model.addAttribute("messageDTO", null);
            } else {
                model.addAttribute("messageDTO", new MessageDTO(action, status,
                        status.equalsIgnoreCase("success")
                                ? "Cập nhật dữ liệu thành công!"
                                : "Vui lòng kiểm tra lại thông tin!"));
            }
            return "back/product_form";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @GetMapping("/form")
    public String create(Model model) {
        try {
            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("productDTO", new ProductDTO());
            model.addAttribute("categoryList", categoryMapper.toListDTO(categoryList));
            return "back/product_form";
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

    @PostMapping("/form")
    public String save(Model model, ProductDTO productDTO, BindingResult bindingResult) {
        try {
            productValidator.validate(productDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                List<Category> categoryList = categoryService.findAll();
                model.addAttribute("productDTO", productDTO);
                model.addAttribute("categoryList", categoryMapper.toListDTO(categoryList));
                return "back/product_form";
            }

            Product product = productMapper.toEntity(productDTO);

            if (productDTO.getImageMul() != null && !ObjectUtils.isEmpty(productDTO.getImageMul().getOriginalFilename())) {
                FileDTO fileDTOBack = fileUploadService.uploadFile(productDTO.getImageMul());
                product.setImage(fileDTOBack.getPath());
            }

            productService.save(product);

            String redirectUrl = "/back/product/form/"+ product.getId() + "?action=save&status=success";
            return "redirect:" + redirectUrl;
        } catch (Exception exception) {
            return "redirect:" + REDIRECT_URL;
        }
    }

}
