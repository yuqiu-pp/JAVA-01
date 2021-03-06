package com.spring.sphere.jdbc.mapper;

import com.spring.sphere.jdbc.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper  {
    int insert(OrderInfo orderInfo);

    List<OrderInfo> selectAll();
}
