package com.multi.manualsource.mapper.master;

import com.multi.manualsource.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserUpdateMapper {
    int insert(User user);
}
