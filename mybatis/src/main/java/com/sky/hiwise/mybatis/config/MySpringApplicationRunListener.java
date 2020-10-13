package com.sky.hiwise.mybatis.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

public class MySpringApplicationRunListener implements SpringApplicationRunListener {


    private CuratorFramework client;

    private ApplicationContext applicationContext;

    public MySpringApplicationRunListener(SpringApplication application, String[] args) {

    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("MySpringApplicationRunListener:environmentPrepared");

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        applicationContext = context;
        System.out.println("MySpringApplicationRunListener:contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("MySpringApplicationRunListener:contextLoaded");

    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        client = (CuratorFramework)context.getBean("zkCuratorClient");
        try {
            NodeCache nodeCache = new NodeCache(client, "/test");
            nodeCache.start();
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("test", new String(nodeCache.getCurrentData().getData()));
                    PropertySource my = new MapPropertySource("test", map);
                    environment.getPropertySources().addLast(my);
                    System.out.println("MySpringApplicationRunListener started zkCuratorClient");
                    System.out.println(environment.getPropertySources().get("test").getProperty("test"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
