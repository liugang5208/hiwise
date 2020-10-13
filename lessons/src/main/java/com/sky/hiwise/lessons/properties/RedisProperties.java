package com.sky.hiwise.lessons.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {


    private Integer maxTotal; //最大连接数

    private Integer maxIdle;//在jedispool中最大的idle状态(空闲的)的jedis实例的个数

    private Integer minIdle;//在jedispool中最小的idle状态(空闲的)的jedis实例的个数

    private Boolean testOnBorrow;//在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。

    private Boolean testOnReturn;//在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。

    private List<Map<String, String>> address = new ArrayList<>();
}
