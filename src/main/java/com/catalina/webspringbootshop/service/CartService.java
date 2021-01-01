package com.catalina.webspringbootshop.service;

import com.catalina.webspringbootshop.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface CartService {
    void addProduct(Product product);
    void removeProduct(Product product);
    void clearProducts();
    Map<Product, Integer> productsInCart();
    BigDecimal totalPrice();
    void cartCheckout();
}
