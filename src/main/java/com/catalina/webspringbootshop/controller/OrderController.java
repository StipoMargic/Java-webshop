package com.catalina.webspringbootshop.controller;


import com.catalina.webspringbootshop.dto.OrderRequest;
import com.catalina.webspringbootshop.dto.UserRequest;
import com.catalina.webspringbootshop.entity.Order;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/createOrder")
    public void create(@RequestBody OrderRequest request) {
        System.out.println("Sent");
    }

    @GetMapping("/orders")
    public List<Order> list() {
        return orderRepository.findAll();
    }
}
