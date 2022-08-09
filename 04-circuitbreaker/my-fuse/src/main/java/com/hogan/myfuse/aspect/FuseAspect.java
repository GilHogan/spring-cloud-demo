package com.hogan.myfuse.aspect;

import com.hogan.myfuse.model.Fuse;
import com.hogan.myfuse.model.FuseStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@Aspect
public class FuseAspect {

    // 因为一个消费者可以去调用多个提供者  每个提供者都有自己的断路器
    // 在消费者里面去创建一个断路器的容器
    public static Map<String, Fuse> fuseMap = new HashMap<>();

    static {
        // 假设 是需要去调用order-service的服务
        fuseMap.put("order-service", new Fuse());
    }

    Random random = new Random();

    @Around(value = "@annotation(com.hogan.myfuse.anno.MyFuse)")
    public Object fuseAround(ProceedingJoinPoint joinPoint) {
        Object result = null;
        Fuse fuse = fuseMap.get("order-service");
        FuseStatus status = fuse.getStatus();
        switch (status) {
            case CLOSE:
                // 正常 去调用
                try {
                    result = joinPoint.proceed();
                    return result;
                } catch (Throwable e) {
                    fuse.addFailCount();
                    return "调用失败";
                }
            case OPEN:
                // 不能调用
                return "status open 不能调用";
            case HALF_OPEN:
                // 可以用少许流量去调用
                int i = random.nextInt(5);
                System.out.println(i);
                if (i == 1) {
                    try {
                        result = joinPoint.proceed();
                        // 调用成功了 断路器关闭
                        fuse.setStatus(FuseStatus.CLOSE);
                        synchronized (fuse.getLock()) {
                            fuse.getLock().notifyAll();
                        }
                        return result;
                    } catch (Throwable e) {
                        return "调用失败";
                    }
                }
            default:
                return "调用失败";
        }
    }
}
