package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.entity.CartItem;
import com.catalina.webspringbootshop.entity.Product;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;


    @GetMapping("/cartt")
    public String showShoppingCart(Model model, @AuthenticationPrincipal Authentication authentication) {

        User user = userService.getCurrentlyLoggedInUser(authentication);
        List<CartItem> cartItems = cartService.listCartItems(user);

        model.addAttribute("cartItems", cartItems);
        return "cartt";
    }

    @GetMapping("/cartt/add/{id}/{qty}")
    public String add(@PathVariable("id") int id, @PathVariable("qty") int quantity,
                      @AuthenticationPrincipal Authentication authentication) {
        System.out.println("addproducttocart" + id + "-" + quantity);
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/";
        }
        User user = userService.getCurrentlyLoggedInUser(authentication);

        Integer addedQuantity = cartService.addProduct(id, quantity, user);
        System.out.println("product added");

        return "redirect:/cartt";
    }

    @RequestMapping(value = "/cartt/update/{id}/{qty}", method = {RequestMethod.POST})
    public String update(@PathVariable("id") int id, @PathVariable("qty") int quantity,
                      @AuthenticationPrincipal Authentication authentication) {

        User user = userService.getCurrentlyLoggedInUser(authentication);

        if(user == null) {
            return "redirect: /";
        }

        cartService.updateQuantity(id, quantity, user);
        return "redirect:/cartt";
    }

    @PostMapping("/cartt/remove/{id}")
    public String remove(@PathVariable("id") int id,
                         @AuthenticationPrincipal Authentication authentication) {
        User user = userService.getCurrentlyLoggedInUser(authentication);

        if(user == null) {
            return "redirect: /";
        }
        cartService.removeProduct(id, user);

        return "redirect:/cartt";
    }
}
