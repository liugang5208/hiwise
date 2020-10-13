package com.sky.hiwise.autoconfigure.configuration;

import org.springframework.context.annotation.Bean;

public class TestConfiguration {

    @Bean
    public String test() { // 方法名即 Bean 名称
        System.out.println("this is test");
        return "test 2018";
    }
}
