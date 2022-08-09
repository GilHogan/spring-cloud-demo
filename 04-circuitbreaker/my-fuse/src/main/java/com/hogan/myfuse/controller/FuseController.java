package com.hogan.myfuse.controller;

import com.hogan.myfuse.anno.MyFuse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FuseController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("doRpc")
    @MyFuse
    public String doRpc() {
        String result = restTemplate.getForObject("http://localhost:8989/test", String.class);
        return result;
    }
}
