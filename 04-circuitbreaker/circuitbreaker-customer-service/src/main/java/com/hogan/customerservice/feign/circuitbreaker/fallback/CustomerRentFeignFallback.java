package com.hogan.customerservice.feign.circuitbreaker.fallback;


import com.hogan.customerservice.feign.CustomerRentFeign;
import org.springframework.stereotype.Component;

/**
 * 这里需要加入ioc容器
 */
@Component
public class CustomerRentFeignFallback implements CustomerRentFeign {

    /**
     * 这个方法就是备选方案
     */
    @Override
    public String rent() {
        return "我是备胎";
    }
}
