package com.example.transporter.controller.front;

import com.example.transporter.model.dto.SearchDTO;
import com.example.transporter.model.entity.Category;
import com.example.transporter.model.entity.Product;
import com.example.transporter.model.mapper.ProductMapper;
import com.example.transporter.service.CategoryService;
import com.example.transporter.service.ProductService;
import com.example.transporter.utils.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private static final String DEFAULT_PAGE = "1";

    private static final long DEFAULT_CATEGORY = 0;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping(value = {"", "/"})
    public String list(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE, required = false) int pageNumber) {
        return listByPage(model, pageNumber, DEFAULT_CATEGORY, null);
    }

    @PostMapping(value = {"/search"})
    public String search(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE, required = false) int pageNumber,
                         SearchDTO searchDTO) {
        return listByPage(model, pageNumber, searchDTO.getCategoryId(), searchDTO.getSearch());
    }

    @GetMapping("/{categoryId}")
    public String showProductByCategory(Model model, @RequestParam(value = "page", defaultValue = DEFAULT_PAGE, required = false) int pageNumber,
                                        @PathVariable long categoryId, @RequestParam(value = "search", required = false) String search) {
        return listByPage(model, pageNumber, categoryId, search);
    }

    private String listByPage(Model model, int pageNumber, long categoryId, String search) {

        // pagination
        Pageable pageable = PageRequest.of(pageNumber - 1, ConstantUtil.PAGE_SIZE, Sort.by("title").ascending());
        Page<Product> productList;

//        if (search != null && !search.equalsIgnoreCase("")) {
//            productList = productService.searchByTitleAndCategory(search, categoryId, pageable);
//        } else {
//            if (categoryId == DEFAULT_CATEGORY) {
//                productList = productService.findByCategoryAndStatusTrue(null, pageable);
//            } else {
//                Category category = categoryService.findById(categoryId);
//                productList = productService.findByCategoryAndStatusTrue(category, pageable);
//            }
//        }
//        if (search != null && categoryId == DEFAULT_CATEGORY){
//            productList =productService.searchByStatusIsTrueAndTitleContains(search, pageable);
//        }

        if (categoryId == DEFAULT_CATEGORY) {
            if (search != null && !search.equalsIgnoreCase("")) {
                productList = productService.searchByStatusIsTrueAndTitleContains(search, pageable);
            } else {
                productList = productService.findByCategoryAndStatusTrue(null, pageable);
            }
        } else {
            Category category = (categoryId != DEFAULT_CATEGORY) ? categoryService.findById(categoryId) : null;
            if (search != null && !search.equalsIgnoreCase("")) {
                productList = productService.searchByTitleAndCategory(search, categoryId, pageable);
            } else {
                productList = productService.findByCategoryAndStatusTrue(category, pageable);
            }
        }

        List<Product> productListByPage = new ArrayList<>(productList.getContent());

        model.addAttribute("productListDTO", productMapper.toListDTO(productListByPage));
        model.addAttribute("totalPage", productList.getTotalPages());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("search", search);
        model.addAttribute("categoryId", categoryId);

        return "front/shop";
    }

}
