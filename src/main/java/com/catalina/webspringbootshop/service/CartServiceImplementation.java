package com.catalina.webspringbootshop.service;

import com.catalina.webspringbootshop.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartServiceImplementation implements CartService{
    private Map<Product, Integer> cart = new LinkedHashMap<>();

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void removeProduct(Product product) {

    }

    @Override
    public void clearProducts() {

    }

    @Override
    public Map<Product, Integer> productsInCart() {
        return null;
    }

    @Override
    public BigDecimal totalPrice() {
        return null;
    }

    @Override
    public void cartCheckout() {

    }
}
