package com.example.demo12.config;

import cn.hutool.core.thread.ExecutorBuilder;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@EnableAsync
public class AsyncTaskConfig  implements AsyncConfigurer {

/*
    corePoolSize 初始池大小
    maxPoolSize 最大池大小（允许同时执行的最大线程数）
    workQueue 队列，用于存在未执行的线程
    handler 当线程阻塞（block）时的异常处理器，所谓线程阻塞即线程池和等待队列已满，无法处理线程时采取的策略*/


    @Override
    @Bean
    public Executor getAsyncExecutor() {
        return ExecutorBuilder.create()
                .setCorePoolSize(5)
                .setMaxPoolSize(10)
                .setWorkQueue(new LinkedBlockingQueue<>(100))
                .setAllowCoreThreadTimeOut(true)
                .build();
    }


    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
