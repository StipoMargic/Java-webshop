package com.catalina.webspringbootshop.repository;

import com.catalina.webspringbootshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
