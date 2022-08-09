package com.hogan.providerorderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {

    @GetMapping("doOrder")
    public String doOrder() {
        System.out.println("有用户来下单了");

        try {
            // 模拟操作数据库等 耗时2s
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "下单成功";
    }
}
