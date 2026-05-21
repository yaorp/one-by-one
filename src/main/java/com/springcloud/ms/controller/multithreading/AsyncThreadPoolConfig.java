package com.springcloud.ms.controller.multithreading;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义一个线程池，供 @Async 注解使用，如果不指定线程池，默认使用 SimpleAsyncTaskExecutor，性能较差
 * 是spring提供的一个线程池实现，底层使用 ThreadPoolExecutor 来管理线程池的行为。它提供了许多配置选项，可以根据应用程序的需求进行调整。
 */
@EnableAsync
@Configuration
public class AsyncThreadPoolConfig {

    @Bean("puJiangExecutor")
    public Executor puJiangExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 核心线程数
        executor.setCorePoolSize(5);

        // 最大线程数
        executor.setMaxPoolSize(10);

        // 队列容量
        executor.setQueueCapacity(200);

        // 线程名前缀
        executor.setThreadNamePrefix("pujiang-async-");

        // 拒绝策略：由调用线程自己执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 等待任务执行完再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);

        // 最多等待 60 秒
        executor.setAwaitTerminationSeconds(60);

        executor.initialize();
        return executor;
    }
}