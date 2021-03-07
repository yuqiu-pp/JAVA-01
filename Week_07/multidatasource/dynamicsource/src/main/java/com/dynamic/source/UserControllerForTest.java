package com.dynamic.source;

import com.dynamic.source.annotation.ReadOnly;
import com.dynamic.source.config.DataSourceType;
import com.dynamic.source.context.DynamicDataSourceContextHolder;
import com.dynamic.source.entity.User;
import com.dynamic.source.service.UserService;
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
        int res = userService.saveUser(new User("李四", 23));
        return res;
    }

    @ReadOnly
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        // DynamicDataSourceContextHolder.setContextKey(DataSourceType.SLAVE.name());
        List<User> users = userService.queryUser();
        // DynamicDataSourceContextHolder.removeContextKey();
        return users;
    }
}
