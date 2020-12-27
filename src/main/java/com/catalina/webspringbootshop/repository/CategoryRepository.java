package com.catalina.webspringbootshop.repository;

import com.catalina.webspringbootshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
