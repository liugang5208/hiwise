package com.sky.hiwise.study.controller;

import io.micrometer.core.instrument.Tag;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    Redisson redisson;

    @GetMapping(value = "/lock")
    public String lock() {
        String lockKey = "lock_key1";
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock(30, TimeUnit.SECONDS);
            System.out.println(redisTemplate.opsForValue().setIfAbsent("test", "hello", 100, TimeUnit.SECONDS));

        } finally {
            lock.unlock();
        }
        return "hello";
    }
}
