package com.spring.sphere.jdbc.service;

import com.spring.sphere.jdbc.entity.User;

import java.util.List;

public interface UserService {

    int saveUser(User user);

    List<User> queryUser();
}
