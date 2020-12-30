package com.catalina.webspringbootshop.repository;

import com.catalina.webspringbootshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findById(int id);

}
