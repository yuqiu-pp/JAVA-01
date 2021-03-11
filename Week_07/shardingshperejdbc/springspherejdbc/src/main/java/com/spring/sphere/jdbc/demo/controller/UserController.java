package com.spring.sphere.jdbc.demo.controller;

import com.spring.sphere.jdbc.demo.entity.User;
import com.spring.sphere.jdbc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/saveUser")
    public int saveUser() {
        return userService.saveUser(new User("张三", 23));
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.queryUser();
    }

    @GetMapping("/getUsersFromMaster")
    public List<User> queryUserFromMaster() {
        return userService.queryUserFromMaster();
    }
}
