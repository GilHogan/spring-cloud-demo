package com.hogan.ordercenter.controller;

import com.hogan.projectdomain.domain.Order;
import com.hogan.commonapi.feign.UserOrderFeign;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class OrderController implements UserOrderFeign {

    @Override
    public Order getOrderByUserId(Integer userId) {
        System.out.println(userId);
        try {
            TimeUnit.SECONDS.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Order order = Order.builder()
                .name("番茄盖饭")
                .price(15D)
                .orderId(1)
                .build();
        return order;
    }
}
