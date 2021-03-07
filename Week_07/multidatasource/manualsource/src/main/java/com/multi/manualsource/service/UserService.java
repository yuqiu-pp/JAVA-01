package com.multi.manualsource.service;

import com.multi.manualsource.entity.User;

import java.util.List;

public interface UserService {
    int saveUser(User user);

    List<User> queryUser();
}
