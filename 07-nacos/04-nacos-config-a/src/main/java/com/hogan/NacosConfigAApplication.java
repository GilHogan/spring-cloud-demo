package com.hogan;

import com.hogan.config.HeroConfig;
import com.hogan.config.UserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties({HeroConfig.class, UserConfig.class})
public class NacosConfigAApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigAApplication.class, args);

        String userName = applicationContext.getEnvironment().getProperty("hero.name");
        String userAge = applicationContext.getEnvironment().getProperty("hero.age");
        System.err.println("user name :"+userName+"; age: "+userAge);

        System.err.println("a_group test name :"+ applicationContext.getEnvironment().getProperty("agroup.name"));
        System.err.println("refresh-test value :"+ applicationContext.getEnvironment().getProperty("refresh-test.value"));

    }

}
