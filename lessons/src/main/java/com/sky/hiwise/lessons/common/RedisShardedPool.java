package com.sky.hiwise.lessons.common;

import com.sky.hiwise.lessons.properties.RedisProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by geely
 */
@Component
public class RedisShardedPool {

    private static ShardedJedisPool pool;//jedis连接池

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

        JedisShardInfo info1 = new JedisShardInfo(redisProperties.getAddress().get(0).get("ip"), Integer.parseInt(redisProperties.getAddress().get(0).get("port")),1000*2);

        JedisShardInfo info2 = new JedisShardInfo(redisProperties.getAddress().get(1).get("ip"), Integer.parseInt(redisProperties.getAddress().get(1).get("port")),1000*2);

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);

        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);

        pool = new ShardedJedisPool(config,jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    public static ShardedJedis getJedis(){
        if (pool == null) {
            initPool();
        }
        return pool.getResource();
    }


    public static void returnBrokenResource(ShardedJedis jedis){
        pool.returnBrokenResource(jedis);
    }



    public static void returnResource(ShardedJedis jedis){
        pool.returnResource(jedis);
    }


    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();

        for(int i =0;i<10;i++){
            jedis.set("key"+i,"value"+i);
        }
        returnResource(jedis);

//        pool.destroy();//临时调用，销毁连接池中的所有连接
        System.out.println("program is end");


    }
}
