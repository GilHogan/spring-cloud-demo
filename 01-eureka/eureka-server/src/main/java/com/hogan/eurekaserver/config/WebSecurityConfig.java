package com.hogan.eurekaserver.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Eureka会自动配置CSRF防御机制,spring security认为post,put and delete http methods 都是有风险的
 * ,如果这些method发送过程中没有带上CSRF token,会被直接拦截并返回403 forbidden
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 方案一 过滤指定路径
     * @param http
     * @throws Exception
     */
    /*@Override
    protected void configure1(HttpSecurity http) throws Exception {
        //这句为了访问eureka控制台和/actuator时能做安全控制
        super.configure(http);
        //忽略指定路径的所有请求
        http.csrf().ignoringAntMatchers("/eureka/**");
    }*/

    /**
     * 方案二 保持密码验证的同时禁用CSRF防御机制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //如果直接用disable()会把安全验证也警用掉
        http.csrf().disable().authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}