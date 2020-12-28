package com.catalina.webspringbootshop.controller;

import com.catalina.webspringbootshop.dto.UserUpdate;
import com.catalina.webspringbootshop.entity.User;
import com.catalina.webspringbootshop.repository.UserRepository;
import com.catalina.webspringbootshop.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{id}", method = {RequestMethod.GET})
    public String get(ModelMap model, @PathVariable("id") int id, HttpServletRequest req) {
        User user = userRepository.findById(id);
        if (null == user) {
            return "error";
        }

        if (StringUtils.equals(req.getMethod(), RequestMethod.GET.toString())) {
            model.addAttribute("user", user);
            return "users";
        }

        return "err";
    }

    @RequestMapping(value = "/user/edit/{id}", method = {RequestMethod.POST})
    public String update(ModelMap model, @PathVariable("id") int id, HttpServletRequest req, UserUpdate user) {
        User u = userRepository.findById(id);

        if (u == null) {
            return "error";
        }
        if (StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            userService.edit(u, user);
            logger.debug(String.format("User with id: %s has been successfully edited.", id));
        }

        return "error";
    }

    @RequestMapping(value = "/user/delete/{id}", method = {RequestMethod.POST})
    public String destroy(@PathVariable("id") int id, HttpServletRequest req) {
        User u = userRepository.findById(id);

        if (u == null) {
            return "error";
        }
        if (StringUtils.equals(req.getMethod(), RequestMethod.POST.toString())) {
            userRepository.delete(u);
            logger.debug(String.format("User with id: %s has been successfully removed.", id));
            return "redirect:/";
        }

        return "error";
    }

    @GetMapping("/users")
    public String list(ModelMap model) {
        model.addAttribute("all_users", list());

        return "users";
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
