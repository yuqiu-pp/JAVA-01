package com.spring.sphere.jdbc.service;

import com.spring.sphere.jdbc.entity.OrderInfo;
import com.spring.sphere.jdbc.entity.User;

import java.util.List;

public interface OrderInfoService {

    int saveOrderInfo(OrderInfo orderInfo);

    List<OrderInfo> queryOrderInfo();
}
