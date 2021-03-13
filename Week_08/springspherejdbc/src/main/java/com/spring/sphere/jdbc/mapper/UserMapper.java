package com.spring.sphere.jdbc.mapper;

import com.spring.sphere.jdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User user);

    List<User> selectAll();
}
