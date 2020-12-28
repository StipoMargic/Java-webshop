package com.catalina.webspringbootshop.seeders;

import com.catalina.webspringbootshop.config.Roles;
import com.catalina.webspringbootshop.entity.*;
import com.catalina.webspringbootshop.repository.CategoryRepository;
import com.catalina.webspringbootshop.repository.OrderRepository;
import com.catalina.webspringbootshop.repository.ProductRepository;
import com.catalina.webspringbootshop.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseSeeder {

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public DatabaseSeeder(
            UserRepository userRepository,
            JdbcTemplate jdbcTemplate,
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedCategoryTable();
        seedProductTable();
        seedOrderTable();
    }

    private void seedUsersTable() {
        String sql = "SELECT username, email FROM users U WHERE U.username = \"stipo\" OR " +
                "U.email = \"stipo@liberato.io\" LIMIT 1";
        List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (u == null || u.size() <= 0) {
            User user = new User("Stipo", "stipo@liberato.io", new BCryptPasswordEncoder().encode("password"), Roles.ADMIN.toString());
            user.setRole(Roles.ADMIN.toString());
            User user1 = new User("tomo", "tomo@liberato.io", new BCryptPasswordEncoder().encode("password"), Roles.ADMIN.toString());
            user1.setRole(Roles.ADMIN.toString());
            userRepository.save(user);
            userRepository.save(user1);
            logger.info("Users Seeded");
        } else {
            logger.info("Stipo is already in db!");
        }
    }

    private void seedCategoryTable() {
        String sql = "SELECT name FROM category C WHERE C.name = \"laptop\" LIMIT 1";
        List<Category> c = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (c == null || c.size() <= 0) {
            Category category = new Category("laptop");
            categoryRepository.save(category);
            logger.info("Category Seeded");
        } else {
            logger.info("Category laptop is already in db!");
        }
    }

    private void seedProductTable() {
        String sql = "SELECT name FROM products P WHERE P.name = \"Iphone12\" LIMIT 1";
        List<Category> p = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (p == null || p.size() <= 0) {
            Product product = new Product("Iphone12", 500.0f, 4, "dsao");
            productRepository.save(product);
            logger.info("Product Seeded");
        } else {
            logger.info("Product iphone12 already in db!");
        }
    }

    private void seedOrderTable() {
            Set<Product> a = new HashSet<>();
            a.add(productRepository.findById(1).orElseThrow());
            //System.out.println(a.size());

            Order order = new Order(userRepository.findById(1), 101, 10, new Date(),a);
            orderRepository.save(order);
        }
    }
