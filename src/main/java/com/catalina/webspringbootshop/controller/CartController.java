package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.entity.Product;
import com.catalina.webspringbootshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    public CartController(ProductService productService) {
                this.productService = productService;
    }

    @GetMapping("/cart")
    public String cart(Model model){

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") int id){
        Product product = productService.findById(id);
        if (product != null){
            logger.debug(String.format("Product with id: %s added to shopping cart.", id));
        }
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeProductFromCart(@PathVariable("id") int id){
        Product product = productService.findById(id);
        if (product != null){
            logger.debug(String.format("Product with id: %s removed from shopping cart.", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductsInCart(){

        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout(){

        return "redirect:/cart";
    }
}
