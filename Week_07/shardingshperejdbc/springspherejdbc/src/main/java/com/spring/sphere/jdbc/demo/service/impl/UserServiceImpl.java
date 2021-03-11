package com.spring.sphere.jdbc.demo.service.impl;


import com.spring.sphere.jdbc.demo.annotation.ForceReadMaster;
import com.spring.sphere.jdbc.demo.entity.User;
import com.spring.sphere.jdbc.demo.mapper.UserMapper;
import com.spring.sphere.jdbc.demo.service.UserService;
import org.apache.shardingsphere.api.hint.HintManager;
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

    // 强制读主
    // @Override
    // public List<User> queryUserFromMaster() {
    //     try (
    //             HintManager hintManager = HintManager.getInstance();
    //     ) {
    //         hintManager.setMasterRouteOnly();
    //         List<User> users = userMapper.selectAll();
    //         return users;
    //     }
    // }

    @Override
    @ForceReadMaster
    public List<User> queryUserFromMaster() {
        return userMapper.selectAll();
    }

}
