package com.hogan.customerservice.feign;

import com.hogan.customerservice.feign.circuitbreaker.fallback.CustomerOrderFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 这里需要指定熔断的类
 */
@FeignClient(value = "order-service", fallbackFactory = CustomerOrderFeignFallbackFactory.class)
public interface CustomerOrderFeignReason {

    @GetMapping("doOrder")
    String doOrder();

    @GetMapping("doOrder2")
    String doOrder2();

}
