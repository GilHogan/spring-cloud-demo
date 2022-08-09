package com.hogan.customerservice.feign;

import com.hogan.customerservice.feign.circuitbreaker.fallback.CustomerRentFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 这里需要指定熔断的类
 */
@FeignClient(value = "rent-car-service", fallback = CustomerRentFeignFallback.class)
public interface CustomerRentFeign {

    @GetMapping("rent")
    String rent();


}
