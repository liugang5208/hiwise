package com.sky.hiwise.study.controller;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/limit")
@Slf4j
public class LimitController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    private DefaultRedisScript<Long> redisScript;


    @PostConstruct()
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));
    }


    @GetMapping(value = "/redis")
    public String redis() {
        List<String> keyList = Arrays.asList("limit_redis");
        for (int i = 0; i < 20; i++) {
            Long result = stringRedisTemplate.execute(redisScript, keyList, "10");
            System.out.println(result);
        }

        return "success";
    }

    @GetMapping(value = "/guava")
    public String guava() {
        int count = 10000;
        RateLimiter limit = RateLimiter.create(count);
        if (limit.tryAcquire(1)) {
            System.out.println("get Acquire success");
        }
        for (int i = 0; i < count; i++) {
            System.out.println(limit.acquire(1));
        }
        return "success";
    }

}
