package com.catalina.webspringbootshop.seeders;

import com.catalina.webspringbootshop.config.Roles;
import com.catalina.webspringbootshop.entity.Category;
import com.catalina.webspringbootshop.entity.Product;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.repository.CategoryRepository;
import com.catalina.webspringbootshop.repository.ProductRepository;
import com.catalina.webspringbootshop.repository.UserRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;

@Component
public class DatabaseSeeder {

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private JdbcTemplate jdbcTemplate;
    private CategoryRepository categoryRepository;
    private Faker faker;
    private final int USERS_TO_CREATE = 20;
    private final int PRODUCTS_TO_CREATE = 20;

    @Autowired
    public DatabaseSeeder(
            UserRepository userRepository,
            ProductRepository productRepository,
            JdbcTemplate jdbcTemplate,
            CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void fill() {
        this.faker = generateFaker();
    }

    private Faker generateFaker() {
        return new Faker(new Locale("en-US"));
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedCategoryTable();
        seedProductTable();
    }

    private void seedUsersTable() {
        String sql = "SELECT username, email FROM users U WHERE U.username = \"stipo\" OR " +
                "U.email = \"stipo@liberato.io\" LIMIT 1";
        List<User> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (u == null || u.size() <= 0) {
            User user = new User("Stipo", "stipo@liberato.io", new BCryptPasswordEncoder().encode("password"), Roles.ADMIN.toString());
            user.setRole(Roles.ADMIN.toString());
            userRepository.save(user);
            logger.info("Users Seeded");
        } else {
            logger.info("Stipo is already in db!");
        }
        for (int i = 0; i < this.USERS_TO_CREATE; i++) {
            User fake = new User(faker.name().username(), faker.internet().emailAddress(), new BCryptPasswordEncoder().encode(faker.name().name()), Roles.CUSTOMER.toString());
            userRepository.save(fake);
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
        Category category = categoryRepository.findByName("laptop");

        for (int i = 0; i < this.PRODUCTS_TO_CREATE; i++) {
            Product product = new Product(faker.pokemon().name(), 10, 10, "Testr", category);

            productRepository.save(product);
        }
    }


}