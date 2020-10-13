package com.sky.hiwise.lessons.controller;

import com.alibaba.fastjson.JSONObject;
import com.sky.hiwise.lessons.common.RedisPool;
import com.sky.hiwise.lessons.properties.RedisProperties;
import com.sky.hiwise.lessons.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    TestService testService;



    @GetMapping(value = "/")
    public String test() {

        System.out.println(redisProperties.getMaxTotal());
        Jedis jedis= RedisPool.getJedis();
        jedis.set("test1","geelyvalue");
        return "hello";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        //LinkedList
        return testService.hello("test");
    }

    @DeleteMapping(path = "/del", consumes = "application/json")
    public String delete(@RequestBody JSONObject test) {

        System.out.println(test);
        return test.toString();
    }
}
