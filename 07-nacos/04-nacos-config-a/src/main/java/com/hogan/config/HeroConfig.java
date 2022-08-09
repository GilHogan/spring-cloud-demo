package com.hogan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "hero")
public class HeroConfig {

    private String name;


    private Integer age;


    private String address;


}
