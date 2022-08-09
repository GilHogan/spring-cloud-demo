package com.hogan.customerservice.controller;

import com.hogan.customerservice.feign.CustomerRentFeign;
import com.hogan.customerservice.feign.CustomerOrderFeignReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Qualifier("com.hogan.customerservice.feign.CustomerRentFeign")
    @Autowired
    private CustomerRentFeign customerRentFeign;
    @Autowired
    private CustomerOrderFeignReason customerOrderFeignReason;

    @GetMapping("customerRent")
    public String CustomerRent() {
        System.out.println("客户来租车了");
        // RPC
        return customerRentFeign.rent();
    }

    @GetMapping("customerDoOrder")
    public String CustomerDoOrder() {
        System.out.println("下单开始");
        return customerOrderFeignReason.doOrder();
    }


    @GetMapping("customerDoOrder2")
    public String CustomerDoOrder2() {
        System.out.println("下单开始2");
        return customerOrderFeignReason.doOrder2();
    }

}
