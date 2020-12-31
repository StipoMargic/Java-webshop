package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.entity.Category;
import com.catalina.webspringbootshop.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = {"/categories"})
    public String index(ModelMap model) {
        model.addAttribute("model", listAllCategories());

        return "categories";
    }

    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }
}
