package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.dto.UserRequest;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.repository.ProductRepository;
import com.catalina.webspringbootshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/SaveUser")
    public User saveUser(@RequestBody UserRequest request) {
        return userRepository.save(request.getUser());
    }

    @GetMapping("/FindAllUsers")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
