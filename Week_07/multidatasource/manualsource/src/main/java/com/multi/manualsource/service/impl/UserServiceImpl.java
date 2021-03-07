package com.multi.manualsource.service.impl;


import com.multi.manualsource.entity.User;
import com.multi.manualsource.mapper.master.UserUpdateMapper;
import com.multi.manualsource.mapper.slave.UserQueryMapper;
import com.multi.manualsource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserUpdateMapper userUpdateMapper;

    @Autowired
    private UserQueryMapper userQueryMapper;


    @Override
    public int saveUser(User user) {
        return userUpdateMapper.insert(user);
    }

    @Override
    public List<User> queryUser() {
        List<User> users = userQueryMapper.selectAll();
        return users;
    }
}
