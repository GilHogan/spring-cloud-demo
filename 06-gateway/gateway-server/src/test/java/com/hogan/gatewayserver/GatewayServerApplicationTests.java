package com.hogan.gatewayserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class GatewayServerApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new Date());
    }

}
