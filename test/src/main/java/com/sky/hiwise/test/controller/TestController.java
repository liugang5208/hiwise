package com.sky.hiwise.test.controller;

import com.sky.hiwise.test.annotation.Authentication;
import com.sky.hiwise.test.Form;
import com.sky.hiwise.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@RestController
@Validated
public class TestController {

    @Bean
    public RestTemplate create() {
        return  new RestTemplate();
    }

    @Autowired
    UserService userService;

    @GetMapping("/debug")
    public String debug() {
        userService.test();
        return "success";
    }

    @GetMapping("/poll")
    public DeferredResult<String> helloWorld() {
        DeferredResult<String> result = new DeferredResult<>();
//        result.setResult("Hello,World");
        // 入队操作
//        queue.offer(result);
        println("Hello,World");
        result.onCompletion(() -> {
            println("执行结束");
        });

        result.onTimeout(() -> {
            println("执行超时");
        });
        new Thread(()->{
            try {
                sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setResult("completed sleep");

        }).start();

        return result;
    }
    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("HelloWorldAsyncController[" + threadName + "]: " + object);
    }

    @Authentication("test")
    @GetMapping("/index")
    public String index(Form form) {
        return form.toString();
    }


    @Authentication("test")
    @GetMapping(value = "/test")
    public String test(Form form) {
        System.out.println(form.toString());
        return form.toString();
    }

    @PostMapping(value = "/hello")
    public String test(@NotBlank(message = "11212scw323") @RequestParam(value = "test") String test, HttpServletRequest httpServletRequest) {
        return test.toString();
    }


    @GetMapping("/test1")
    public String test1() throws InterruptedException {
        sleep(2000);
        return "test1";
    }

    @GetMapping("/test2")
    public String test2() throws InterruptedException {
        sleep(2000);
        return "test2";
    }

    private List<Form> formList = new ArrayList<>();
    @GetMapping("/heap")
    public String heap() {
        int i = 0;
        while (true) {
            formList.add(new Form(i++, "test", "www"));
        }
    }

    @GetMapping("/loop")
    public String loop() {
        while (true) {
            System.out.println("1111111111");
        }
    }

    //private Object lock1 = new Object();
    //private Object lock2 = new Object();

    @GetMapping("/deadlock")
    public String deadlock() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(()->{
            synchronized (lock1) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Thread1 over!");
                }
            }

        }).start();

        new Thread(()->{
            synchronized (lock2) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Thread2 over!");
                }
            }

        }).start();

        return "deadlock";
    }
}
