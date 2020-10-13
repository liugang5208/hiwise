package com.sky.hiwise.threadpool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Component
@Slf4j
public class TreadPoolTest {

    @Autowired
    ExecutorService consumerQueueThreadPool;

    @PostConstruct
    public void test() {
        //消费队列
        for (int i = 0; i < 5; i++) {
            final int a = i;
            consumerQueueThreadPool.submit(
                    ()-> {
                        Thread.currentThread().setName("test" + a);
                        log.info("test" + a);
                        //log.info("System.out : " + Thread.currentThread().getId());
                        //int j = 0;
//                        while(true) {
//                            log.info("ThreadId:" +Thread.currentThread().getId()+";test:" + a + ";j:" + j++);
//                        }
                    }
            );
        }
    }


    @PostConstruct
    public void test1() throws InterruptedException, ExecutionException, TimeoutException {
//        CommandOrder commandPhone = new CommandOrder("手机");
//        CommandOrder command = new CommandOrder("电视");
//
//
//        //阻塞方式执行
//        String execute = commandPhone.execute();
//        log.info("execute=[{}]", execute);
//
//        //异步非阻塞方式
//        Future<String> queue = command.queue();
//        String value = queue.get(200, TimeUnit.MILLISECONDS);
//        log.info("value=[{}]", value);
//
//
//        CommandUser commandUser = new CommandUser("张三");
//        String name = commandUser.execute();
//        log.info("name=[{}]", name);
    }
}
