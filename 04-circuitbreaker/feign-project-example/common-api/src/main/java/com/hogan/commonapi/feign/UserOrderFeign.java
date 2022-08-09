package com.hogan.commonapi.feign;


import com.hogan.projectdomain.domain.Order;
import com.hogan.commonapi.feign.fallback.UserOrderFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order-service", fallback = UserOrderFeignFallback.class)
public interface UserOrderFeign {

    // 查询订单
    @GetMapping("/order/getOrderByUserId")
    Order getOrderByUserId(@RequestParam Integer userId);


}
