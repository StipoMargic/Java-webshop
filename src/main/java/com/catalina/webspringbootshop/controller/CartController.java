package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.entity.Product;
import com.catalina.webspringbootshop.service.CartService;
import com.catalina.webspringbootshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    private final ProductService productService;
    private final CartService cartService;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String index(ModelMap model) {
        model.addAttribute("products", cartService.productsInCart());
        model.addAttribute("totalPrice", cartService.totalPrice());

        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String add(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        if (product != null) {
            cartService.addProduct(product);
            logger.debug(String.format("Product with id: %s added to shopping cart.", id));
        }
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        if (product != null) {
            cartService.removeProduct(product);
            logger.debug(String.format("Product with id: %s removed from shopping cart.", id));
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductsInCart() {
        cartService.clearProducts();
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout() {
        cartService.cartCheckout();
        return "redirect:/cart";
    }
}
