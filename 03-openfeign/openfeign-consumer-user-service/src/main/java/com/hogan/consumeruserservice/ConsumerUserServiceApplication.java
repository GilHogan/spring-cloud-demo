package com.hogan.consumeruserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients // 标记feign的客户端
public class ConsumerUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserServiceApplication.class, args);
    }

}
