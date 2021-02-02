package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.entity.Category;
import com.catalina.webspringbootshop.entity.Product;
import com.catalina.webspringbootshop.repository.CategoryRepository;
import com.catalina.webspringbootshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = {"/categories"})
    public String index(ModelMap model) {
        model.addAttribute("model", listAllCategories());

        return "categories";
    }

    @GetMapping(value = {"/category/{id}"})
    public String get(@PathVariable("id") int id, ModelMap model) {
        Category category = categoryRepository.findById(id);

        model.addAttribute("products", listProductsByCategory(category));

        return "category";
    }

    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Product> listProductsByCategory(Category category) { return productRepository.findAllByCategory(category);}
}
