package com.hogan.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.timer.Timer;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {


    @GetMapping("doOrder")
    public String doOrder() {
        return "下单成功";

    }


    @GetMapping("doOrder2")
    public String doOrder2() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "下单成功2";
    }

}
