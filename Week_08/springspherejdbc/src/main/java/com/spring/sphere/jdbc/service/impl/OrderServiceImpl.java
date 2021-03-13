package com.spring.sphere.jdbc.service.impl;


import com.spring.sphere.jdbc.entity.OrderInfo;
import com.spring.sphere.jdbc.entity.User;
import com.spring.sphere.jdbc.mapper.OrderInfoMapper;
import com.spring.sphere.jdbc.mapper.UserMapper;
import com.spring.sphere.jdbc.service.OrderInfoService;
import com.spring.sphere.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> queryOrderInfo() {
        List<OrderInfo> orderInfos = orderInfoMapper.selectAll();
        return orderInfos;
    }

    @Override
    public int saveOrderInfo(OrderInfo orderInfo) {
        return orderInfoMapper.insert(orderInfo);
    }

}
