package com.hogan.consumeruserservice.controller;

import com.hogan.consumeruserservice.domain.Order;
import com.hogan.consumeruserservice.feign.UserOrderFeign;
import feign.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class UserController {

    /**
     * 接口是不能做事情的
     * 如果想做事 必须要有对象
     * 那么这个接口肯定是被创建出代理对象的
     * 动态代理 jdk(java interface 接口 $Proxy )  cglib(subClass 子类)
     * jdk动态代理 只要是代理对象调用的方法必须走 java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Autowired
    public UserOrderFeign userOrderFeign;

    /**
     * 总结
     * 浏览器(前端)-------> user-service(/userDoOrder)-----RPC(feign)--->order-service(/doOrder)
     * feign的默认等待时间60s ,详情见配置类 {@link Request.Options#Options()}
     * 也可以通过配置文件修改 feign.client.config.default.connectTimeout, {@link org.springframework.cloud.openfeign.FeignClientProperties}
     */
    @GetMapping("userDoOrder")
    public String userDoOrder() {
        System.out.println("有用户进来了");
        // 这里需要发起远程调用
        String s = userOrderFeign.doOrder();
        return s;
    }


    @GetMapping("testParam")
    public String testParam() {
        String cxs = userOrderFeign.testUrl("张三", 45);
        System.out.println(cxs);

        String t1 = userOrderFeign.oneParam("李四");
        System.out.println(t1);

        String t = userOrderFeign.oneParam();
        System.out.println(t);

        String t3 = userOrderFeign.oneParamRequiredTrue("sd");
        System.out.println(t3);

        String lg = userOrderFeign.twoParam("王五", 34);
        System.out.println(lg);

        Order order = Order.builder()
                .name("牛排")
                .price(188D)
                .time(new Date())
                .id(1)
                .build();

        String s = userOrderFeign.oneObj(order);
        System.out.println(s);

        String param = userOrderFeign.oneObjOneParam(order, "号然");
        System.out.println(param);
        return "ok";
    }

    /**
     * Sun Mar 20 10:24:13 CST 2022
     * Mon Mar 21 00:24:13 CST 2022  +- 14个小时
     * 1.不建议单独传递时间参数
     * 2.转成字符串   2022-03-20 10:25:55:213 因为字符串不会改变
     * 3.jdk LocalDate 年月日    LocalDateTime 会丢失s
     * 4.改feign的源码
     *
     * @return
     */
    @GetMapping("time")
    public String time() {
        Date date = new Date();
        System.out.println(date);
        String s = userOrderFeign.testTime(date);

        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();

        return s;
    }

}
