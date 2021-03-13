package com.spring.sphere.jdbc.service.impl;


import com.spring.sphere.jdbc.entity.User;
import com.spring.sphere.jdbc.mapper.UserMapper;
import com.spring.sphere.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public int saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> queryUser() {
        List<User> users = userMapper.selectAll();
        return users;
    }
}
