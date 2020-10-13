package com.sky.hiwise.youli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sky.hiwise.youli.mapper")
public class YouliApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouliApplication.class, args);
    }

}
