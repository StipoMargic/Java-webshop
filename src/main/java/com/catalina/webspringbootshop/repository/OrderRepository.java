package com.catalina.webspringbootshop.repository;

import com.catalina.webspringbootshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findById(int id);
    //Order findByOrder_date(LocalDate date);

}
