package com.sky.hiwise.mybatis.config;

import org.apache.curator.framework.CuratorFramework;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 10;
    }


    public void setZkCuratorClient(CuratorFramework zkCuratorClient) {
        this.zkCuratorClient = zkCuratorClient;
    }

    @Autowired
    private CuratorFramework zkCuratorClient;




    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        System.out.println("MyApplicationContextInitializer:initialize");

        System.out.println(zkCuratorClient);
    }
}
