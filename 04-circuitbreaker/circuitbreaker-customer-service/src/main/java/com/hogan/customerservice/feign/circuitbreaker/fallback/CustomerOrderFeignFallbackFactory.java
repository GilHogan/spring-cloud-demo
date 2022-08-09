package com.hogan.customerservice.feign.circuitbreaker.fallback;


import com.hogan.customerservice.feign.CustomerOrderFeignReason;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 这里需要加入ioc容器
 */
@Component
public class CustomerOrderFeignFallbackFactory implements FallbackFactory<CustomerOrderFeignFallbackFactory.FallbackWithFactory> {

    @Override
    public FallbackWithFactory create(Throwable cause) {
        // 访问触发回退的原因，处理异常
        System.out.println("调用接口出现异常");
        cause.printStackTrace();
        return new FallbackWithFactory();
    }

    static class FallbackWithFactory implements CustomerOrderFeignReason {
        /**
         * 这个方法就是备选方案
         */
        @Override
        public String doOrder() {
            return "下单异常";
        }

        @Override
        public String doOrder2() {
            return "下单异常2";
        }

    }
}
