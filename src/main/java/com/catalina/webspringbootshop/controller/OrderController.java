package com.catalina.webspringbootshop.controller;


import com.catalina.webspringbootshop.dto.OrderRequest;
import com.catalina.webspringbootshop.dto.UserRegistration;
import com.catalina.webspringbootshop.dto.UserRequest;
import com.catalina.webspringbootshop.entity.Order;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.repository.OrderRepository;
import com.catalina.webspringbootshop.service.OrderServiceImplementation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImplementation orderServiceImplementation;

    @RequestMapping(value = "/createOrder", method = {RequestMethod.POST})
    public String create(ModelMap model, HttpServletRequest req, RedirectAttributes attr, OrderRequest orderRequest) {
        if (StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            return orderServiceImplementation.saveOrder(orderRequest.getOrder(), attr, req);
        }
        return invalidRequestResponse(attr);
    }

    @GetMapping(value = {"/cart"})
    public String cart() {
        return "cart";
    }

    private String invalidRequestResponse(RedirectAttributes attr) {
        attr.addFlashAttribute("error", "Invalid Request Method");
        return "redirect:/";
    }
}
