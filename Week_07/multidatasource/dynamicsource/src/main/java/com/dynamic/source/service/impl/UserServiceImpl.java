package com.dynamic.source.service.impl;


import com.dynamic.source.entity.User;
import com.dynamic.source.mapper.UserMapper;
import com.dynamic.source.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> queryUser() {
        List<User> users = userMapper.selectAll();
        return users;
    }
}
