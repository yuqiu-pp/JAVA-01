package com.dynamic.source.service;


import com.dynamic.source.entity.User;

import java.util.List;

public interface UserService {

    int saveUser(User user);

    List<User> queryUser();
}
