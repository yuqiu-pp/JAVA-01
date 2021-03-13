package com.spring.sphere.jdbc.controller;

import com.spring.sphere.jdbc.entity.OrderInfo;
import com.spring.sphere.jdbc.entity.User;
import com.spring.sphere.jdbc.service.OrderInfoService;
import com.spring.sphere.jdbc.service.UserService;
import com.spring.sphere.jdbc.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/saveUser")
    public int saveUser() {
        return userService.saveUser(new User("李四", 23));
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.queryUser();
    }
}
