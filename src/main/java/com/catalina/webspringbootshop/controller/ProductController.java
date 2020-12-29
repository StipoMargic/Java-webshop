package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.entity.Product;
import com.catalina.webspringbootshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    //TODO: Wishfull programing
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "/index", "/home"})
    public String home(ModelMap model) {
        model.addAttribute("products", getAllProducts());

        return "home";
    }

    private List<Product> getAllProducts() {
        return productService.findAllByOrderByAsc();
    }

}
