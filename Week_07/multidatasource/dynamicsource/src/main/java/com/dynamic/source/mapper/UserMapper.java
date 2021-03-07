package com.dynamic.source.mapper;

import com.dynamic.source.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User user);

    List<User> selectAll();
}
