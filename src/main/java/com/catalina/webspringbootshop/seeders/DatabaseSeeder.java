package com.catalina.webspringbootshop.seeders;

import com.catalina.webspringbootshop.config.Roles;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(
            UserRepository userRepository,
            JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsersTable();
    }

    private void seedUsersTable() {
        User user = new User("Stipo", "stipo@liberato.io", new BCryptPasswordEncoder().encode("password"), Roles.ADMIN.toString());
        user.setRole(Roles.ADMIN.toString());
        userRepository.save(user);
        logger.info("Users Seeded");
    }
}