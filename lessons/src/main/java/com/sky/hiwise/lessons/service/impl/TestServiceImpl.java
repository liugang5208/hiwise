package com.sky.hiwise.lessons.service.impl;

import com.sky.hiwise.lessons.service.TestService;
import org.springframework.stereotype.Service;

@Service("testServicetest")
public class TestServiceImpl implements TestService {

    @Override
    public String hello(String name) {
        System.out.println("invoke hello！");
        return "hello:" + name;
    }
}
