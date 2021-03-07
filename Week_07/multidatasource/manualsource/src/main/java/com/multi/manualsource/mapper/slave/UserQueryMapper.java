package com.multi.manualsource.mapper.slave;

import com.multi.manualsource.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserQueryMapper {
    List<User> selectAll();
}
