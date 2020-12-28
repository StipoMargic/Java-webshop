package com.catalina.webspringbootshop.seeders;

import com.catalina.webspringbootshop.config.Roles;
import com.catalina.webspringbootshop.entity.Category;
import com.catalina.webspringbootshop.entity.Order;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.repository.CategoryRepository;
import com.catalina.webspringbootshop.repository.OrderRepository;
import com.catalina.webspringbootshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder {

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;
    private CategoryRepository categoryRepository;
    private OrderRepository orderRepository;

    @Autowired
    public DatabaseSeeder(
            UserRepository userRepository,
            JdbcTemplate jdbcTemplate,
            CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRepository = categoryRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
        seedCategoryTable();
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


}   