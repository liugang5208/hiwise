package com.sky.hiwise.lessons.common;

import com.sky.hiwise.lessons.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by geely
 */
@Component
@Slf4j
public class RedissonManager {

    private static RedisProperties redisProperties;

    //可以换成@Inject
    @Resource
    private RedisProperties redisPropertiesAutowired;

    private Config config = new Config();

    private Redisson redisson = null;


    public Redisson getRedisson() {
        return redisson;
    }

//    private static String redis1Ip = redisProperties.getAddress().get(0).get("ip");

//    private static Integer redis1Port = Integer.parseInt(redisProperties.getAddress().get(0).get("port"));


    //private static String redis2Ip = PropertiesUtil.getProperty("redis2.ip");
    //private static Integer redis2Port = Integer.parseInt(PropertiesUtil.getProperty("redis2.port"));

    //@PostConstruct
    private void init(){
        try {
            redisProperties = redisPropertiesAutowired;
            config.useSingleServer()
                    .setAddress(
                            new StringBuilder()
                                    .append(redisProperties.getAddress().get(0).get("ip")).append(":")
                                    .append(Integer.parseInt(redisProperties.getAddress().get(0).get("port")))
                                    .toString()
                    );

            redisson = (Redisson) Redisson.create(config);

            log.info("初始化Redisson结束");
        } catch (Exception e) {
            log.error("redisson init error",e);
        }
    }



}
