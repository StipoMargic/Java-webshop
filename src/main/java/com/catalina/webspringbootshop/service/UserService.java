package com.catalina.webspringbootshop.service;

import com.catalina.webspringbootshop.entity.User;

public interface UserService {
    void save(User user);
    void login(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
}
