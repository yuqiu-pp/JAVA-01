package com.spring.sphere.jdbc.controller;

import com.spring.sphere.jdbc.entity.OrderInfo;
import com.spring.sphere.jdbc.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping("/saveOrder")
    public int saveUser() {
        Random random = new Random();
        Long goodsId;
        while (true) {
            goodsId = random.nextLong();
            if (goodsId > 0) {
                break;
            }
        }
        return orderInfoService.saveOrderInfo(new OrderInfo(goodsId));
    }

    @GetMapping("/getOrders")
    public List<OrderInfo> getUsers() {
        return orderInfoService.queryOrderInfo();
    }
}
