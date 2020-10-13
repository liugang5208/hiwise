package com.sky.hiwise.autoconfigure.configuration;

import org.springframework.context.annotation.Bean;

public class HelloConfiguration {

    @Bean
    public String hello() { // 方法名即 Bean 名称
        System.out.println("this is hello");
        return "hello 2018";
    }
}
