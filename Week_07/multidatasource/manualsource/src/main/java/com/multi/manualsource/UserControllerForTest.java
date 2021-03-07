package com.multi.manualsource;

import com.multi.manualsource.entity.User;
import com.multi.manualsource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerForTest {

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
}
