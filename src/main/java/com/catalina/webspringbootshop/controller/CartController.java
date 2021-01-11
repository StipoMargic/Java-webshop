package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.entity.CartItem;
import com.catalina.webspringbootshop.entity.Product;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.repository.UserRepository;
import com.catalina.webspringbootshop.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private UserRepository userRepository;

    private final double TAX = 0.2533;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername());
        List<CartItem> cartItems = cartService.listCartItems(user);
        double cartSum = cartItems.stream().mapToDouble(o -> o.getProduct().getPrice()).sum();
        double totalCartSum = Math.floor((cartSum + cartSum * TAX) * 100) / 100;
        int totalCartItems = cartItems.stream().mapToInt(el -> el.getQuantity()).sum();
        boolean disableCheckoutButton = cartItems.isEmpty();

        model.addAttribute("userDetails", userDetails);
        model.addAttribute("cartSum", cartSum);
        model.addAttribute("totalCartSum", totalCartSum);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalCartItems", totalCartItems);
        model.addAttribute("disableCheckoutButton", disableCheckoutButton);

        return "cart";
    }

    @GetMapping("/cart/add/{id}/{qty}")
    public String add(@PathVariable("id") int id, @PathVariable("qty") int quantity,
                      @AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails == null || userDetails instanceof AnonymousAuthenticationToken) {
            return "redirect:/";
        }
        User user = userRepository.findByUsername(userDetails.getUsername());

        Integer addedQuantity = cartService.addProduct(id, quantity, user);
        System.out.println("product added");

        return "redirect:/cart";
    }

    @RequestMapping(value = "/cartt/update/{id}/{qty}", method = {RequestMethod.POST})
    public String update(@PathVariable("id") int id, @PathVariable("qty") int quantity,
                         @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername());

        if(user == null) {
            return "redirect:/";
        }

        cartService.updateQuantity(id, quantity, user);
        return "redirect:/cartt";
    }

    @GetMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") int id,
                         @AuthenticationPrincipal UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername());

        if(user == null) {
            return "redirect:/";
        }
        cartService.removeProduct(id, user);

        return "redirect:/cart";
    }
}
