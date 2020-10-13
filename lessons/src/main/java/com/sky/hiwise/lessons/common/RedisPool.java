package com.sky.hiwise.lessons.common;

import com.sky.hiwise.lessons.properties.RedisProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class RedisPool {

    private static JedisPool pool;//jedis连接池

    //@Autowired
    private static RedisProperties redisProperties;

    //可以换成@Inject
    @Resource
    private RedisProperties redisPropertiesAutowired;


    @PostConstruct
    public void init() {
        redisProperties = redisPropertiesAutowired;
    }

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(redisProperties.getMaxTotal());
        config.setMaxIdle(redisProperties.getMaxIdle());
        config.setMinIdle(redisProperties.getMinIdle());

        config.setTestOnBorrow(redisProperties.getTestOnBorrow());
        config.setTestOnReturn(redisProperties.getTestOnReturn());

        config.setBlockWhenExhausted(true);//连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。

        pool = new JedisPool(config, redisProperties.getAddress().get(0).get("ip"), Integer.parseInt(redisProperties.getAddress().get(0).get("port")),1000*2);
    }

//    static {
//        initPool();
//    }

    public static Jedis getJedis(){
        if (pool == null) {
            initPool();
        }
        System.out.println(redisProperties.getMaxTotal());
        return pool.getResource();
    }


    public static void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }



    public static void returnResource(Jedis jedis){
        pool.returnResource(jedis);
    }


//    public static void main(String[] args) {
//        Jedis jedis = pool.getResource();
//        jedis.set("geelykey","geelyvalue");
//        returnResource(jedis);
//
//        pool.destroy();//临时调用，销毁连接池中的所有连接
//        System.out.println("program is end");
//
//
//    }
}
