package com.sky.hiwise.youli.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class HandlerThreadPool {

    @Bean(value = "crawlTaskService")
    public ExecutorService buildThreadPool(){
        ExecutorService pool = new ThreadPoolExecutor(
                20,
                50,
                10,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(1000),
                new CustomThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

    private class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = "crawlTask--" + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

}
