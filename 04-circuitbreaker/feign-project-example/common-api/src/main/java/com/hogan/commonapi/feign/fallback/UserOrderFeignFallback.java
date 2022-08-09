package com.hogan.commonapi.feign.fallback;

import com.hogan.projectdomain.domain.Order;
import com.hogan.commonapi.feign.UserOrderFeign;
import org.springframework.stereotype.Component;

@Component
public class UserOrderFeignFallback implements UserOrderFeign {

    /**
     * 一般远程调用的熔断可以直接返回null
     * @param userId
     * @return
     */
    @Override
    public Order getOrderByUserId(Integer userId) {
        return null;
    }
}
