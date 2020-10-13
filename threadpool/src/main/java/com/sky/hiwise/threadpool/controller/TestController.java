package com.sky.hiwise.threadpool.controller;

import com.sky.hiwise.threadpool.histrix.CommandOrder;
import com.sky.hiwise.threadpool.histrix.CommandUser;
import com.sky.hiwise.threadpool.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExecutorService consumerQueueThreadPool;

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String index() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        Future<String> f1 = consumerQueueThreadPool.submit(()-> {
            log.info("stream:{},thread id:{}", Thread.currentThread().getName(), Thread.currentThread().getId());

            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8088/test1", String.class);
            String body = responseEntity.getBody();
            return body;
        });
        Future<String> f2 = consumerQueueThreadPool.submit(()-> {
            log.info("stream:{},thread id:{}", Thread.currentThread().getName(), Thread.currentThread().getId());

            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8088/test2", String.class);
            String body = responseEntity.getBody();
            return body;
        });
//        Future<String> f3 = consumerQueueThreadPool.submit(()-> {
//            log.info("stream:{},thread id:{}", Thread.currentThread().getName(), Thread.currentThread().getId());
//
//            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8088/test1", String.class);
//            String body = responseEntity.getBody();
//            return body;
//        });
//        Future<String> f4 = consumerQueueThreadPool.submit(()-> {
//            log.info("stream:{},thread id:{}", Thread.currentThread().getName(), Thread.currentThread().getId());
//
//            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8088/test1", String.class);
//            String body = responseEntity.getBody();
//            return body;
//        });
        String body1 = f1.get();
        String body2 = f2.get();
        //String body3 = f3.get();
        //String body4 = f4.get();
        System.out.println("body1:" + body1);
        System.out.println("body2:" + body2);
        long endTime = System.currentTimeMillis();
        long tm = endTime - startTime;
        return String.valueOf(tm);
    }

    @GetMapping("/index1")
    public String index1() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ResponseEntity<String> responseEntity1 = restTemplate.getForEntity("http://127.0.0.1:8088/test1", String.class);
        String body1 = responseEntity1.getBody();
        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity("http://127.0.0.1:8088/test2", String.class);
        String body2 = responseEntity2.getBody();

        System.out.println("body1:" + body1);
        System.out.println("body2:" + body2);
        long endTime = System.currentTimeMillis();
        long tm = endTime - startTime;
        return String.valueOf(tm);
    }

    @GetMapping("/index2")
    public String index2() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        CommandOrder commandOrder = new CommandOrder("order", restTemplate);
        CommandUser commandUser = new CommandUser("user", restTemplate);

        //阻塞方式执行
        String order = commandOrder.execute();
        log.info("order=[{}]", order);

        String user = commandUser.execute();
        log.info("user=[{}]", user);

        long endTime = System.currentTimeMillis();
        long tm = endTime - startTime;
        return String.valueOf(tm);
    }

    @GetMapping("/index3")
    public String index3() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        CommandOrder commandOrder = new CommandOrder("order-index3", restTemplate);
        CommandUser commandUser = new CommandUser("user-index3", restTemplate);

        //异步非阻塞方式
        Future<String> queueOrder = commandOrder.queue();
        Future<String> queueUser = commandUser.queue();

        String order = queueOrder.get();
        log.info("order-index3=[{}]", order);

        //异步非阻塞方式
        String user = queueUser.get();
        log.info("user-index3=[{}]", user);

        long endTime = System.currentTimeMillis();
        long tm = endTime - startTime;
        return String.valueOf(tm);
    }

    @GetMapping("/stream")
    public String stream() {
        long startTime = System.currentTimeMillis();

        List<String> locations = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
        List<String> collect = locations.parallelStream().map(location -> {
            log.info("stream:{},thread id:{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8088/test2?test=" + location , String.class);
            return responseEntity.getBody();
        }).collect(Collectors.toList());
        System.out.println("collect:" + collect);
        long endTime = System.currentTimeMillis();
        long tm = endTime - startTime;
        return String.valueOf(tm);
    }

    @GetMapping("/task")
    public String taskExecutor() {
        userService.hello();
        return "success";
    }
}
