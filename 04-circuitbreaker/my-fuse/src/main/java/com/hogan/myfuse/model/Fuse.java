package com.hogan.myfuse.model;

import lombok.Data;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 这个是断路器的模型
 */
@Data
public class Fuse {

    /**
     * 窗口时间
     */
    public static final Integer WINDOW_TIME = 20;

    /**
     * 最大失败次数
     */
    public static final Integer MAX_FAIL_COUNT = 3;


    /**
     * 断路器中有它自己的状态
     */
    private FuseStatus status = FuseStatus.CLOSE;


    /**
     * 当前失败了几次
     * AtomicInteger 线程安全
     */
    private AtomicInteger currentFailCount = new AtomicInteger(0);

    private final Object lock = new Object();

    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            4, 8, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2000),
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
    );

    {
        poolExecutor.execute(() -> {
            // 定期删除
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                    this.currentFailCount.set(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 如果断路器是开的 那么 不会去调用  就不会有失败  就不会记录次数 没有必要清零  这个线程可以不执行
                if (this.status.equals(FuseStatus.CLOSE)) {
                    // 清零
                    this.currentFailCount.set(0);
                } else {
                    // 半开或者不可 不需要清零; 这个线程可以不工作
                    synchronized (lock) {
                        try {
                            lock.wait();
                            System.out.println("清零线程又被唤醒了");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * 记录失败次数
     */
    public void addFailCount() {
        int i = currentFailCount.incrementAndGet(); // ++i;
        if (i >= MAX_FAIL_COUNT) {
            // 失败次数到达阈值
            // 修改当前状态为 open
            this.setStatus(FuseStatus.OPEN);
            // 档熔断器打开以后 就不能访问了，需要将他变成半开
            // 等待一个时间窗口，让熔断器变成半开状态
            poolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(WINDOW_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.setStatus(FuseStatus.HALF_OPEN);
                // 重置失败次数
                this.currentFailCount.set(0);

            });
        }
    }
}
