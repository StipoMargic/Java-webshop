package com.catalina.webspringbootshop.service;

import com.catalina.webspringbootshop.entity.Order;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface OrderService {
    String saveOrder(Order order, RedirectAttributes attr, HttpServletRequest req);
    Order findById(int id);
}
