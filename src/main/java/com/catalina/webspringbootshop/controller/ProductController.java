package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.entity.Product;
import com.catalina.webspringbootshop.repository.ProductRepository;
import com.catalina.webspringbootshop.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping(value = {"/products"})
    public String index(ModelMap model) {
        model.addAttribute("products", getAllProducts());

        return "products";
    }

    @GetMapping(value = {"/{id}"})
    public String get(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("products", productRepository.findById(id));

        return "product";
    }

    @RequestMapping(value = "/product/new", method = {RequestMethod.POST, RequestMethod.GET})
    public String newProduct(ModelMap model, Product product, HttpServletRequest req, RedirectAttributes attr) {
        if (StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            model.addAttribute("productForm", new Product());

            return "product-add";
        }

        if (StringUtils.equals(req.getMethod(), RequestMethod.GET.toString())) {
            productService.save(product);
            logger.debug(String.format("Product with id: %s successfully created.", product.getId()));

            return "redirect:/";
        }

        return invalidRequestResponse(attr);
    }

    @RequestMapping(value = "/product/edit/{id}", method = {RequestMethod.POST})
    public String update(@PathVariable("id") int id, Product product, HttpServletRequest req, RedirectAttributes attr) {
        if (StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            productService.edit(id, product);
            logger.debug(String.format("Product with id: %s has been successfully edited.", id));

            return "redirect:/";
        }

        return invalidRequestResponse(attr);
    }

    @RequestMapping(value = "/product/delete/{id}", method = {RequestMethod.POST})
    public String destroy(@PathVariable("id") int productId) {
        Product product = productService.findById(productId);

        if (product != null) {
            productService.delete(productId);
            logger.debug(String.format("Product with id: %s successfully deleted.", product.getId()));
            return "redirect:/";
        }

        return "error/404";
    }

    private List<Product> getAllProducts() {
        return productService.findAllByOrderByAsc();
    }


    private String invalidRequestResponse(RedirectAttributes attr) {
        attr.addFlashAttribute("error", "Invalid Request Method");
        return "redirect:/";
    }
}
