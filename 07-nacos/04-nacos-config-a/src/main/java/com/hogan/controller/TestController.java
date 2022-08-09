package com.hogan.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hogan.config.HeroConfig;
import com.hogan.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class TestController {

    @Autowired
    private HeroConfig heroConfig;
    @Autowired
    private UserConfig userConfig;

    @GetMapping("info")
    public String heroInfo() {
        return heroConfig.getName() + ":" + heroConfig.getAge() + ":" + heroConfig.getAddress();
    }


    @GetMapping("user")
    public String getUser() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(userConfig);
    }

    @GetMapping("config")
    public String getConfig()  {
        try {
            String serverAddr = "localhost:8848";
            String dataId = "nacos-config";
            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "ok";
    }

}
