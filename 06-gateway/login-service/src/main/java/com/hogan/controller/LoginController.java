package com.hogan.controller;

import com.hogan.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

@RestController
@CrossOrigin // 加上这个注解之后 这个controller里面的方法就可以直接被访问了
public class LoginController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("doLogin")
    @CrossOrigin
    public String doLogin(String name, String pwd) {
        System.out.println(name);
        System.out.println(pwd);
        // 这里假设去做了登录
        User user = new User(1, name, pwd, 18);
        // token
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(token, user.toString(), Duration.ofSeconds(7200));
        return token;
    }

    @GetMapping("test")
    @CrossOrigin
    public String test() {
        return "测试";
    }

}
