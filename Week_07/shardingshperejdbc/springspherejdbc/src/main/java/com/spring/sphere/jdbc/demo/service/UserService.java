package com.spring.sphere.jdbc.demo.service;

import com.spring.sphere.jdbc.demo.entity.User;

import java.util.List;

public interface UserService {

    int saveUser(User user);

    List<User> queryUser();

    public List<User> queryUserFromMaster();
}
