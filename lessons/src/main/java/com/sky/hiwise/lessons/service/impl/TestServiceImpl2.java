package com.sky.hiwise.lessons.service.impl;

import com.sky.hiwise.lessons.service.TestService;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl2 implements TestService {
    @Override
    public String hello(String name) {
        System.out.println("invoke TestServiceImpl2 hello");
        return "TestServiceImpl2 hello:" + name;
    }
}
