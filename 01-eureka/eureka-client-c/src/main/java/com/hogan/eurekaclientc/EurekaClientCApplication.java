package com.hogan.eurekaclientc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaClientCApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientCApplication.class, args);
    }

}
