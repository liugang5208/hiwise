package com.sky.hiwise.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zlj
 * @title: GuavaCacheUtil
 * @projectName order
 * @description:
 * @date 2019-06-13 17:42
 */
@Slf4j
public class GuavaCacheUtil {
    /**
     * 使用ConcurrentHashMap，保证同一个服务器，线程操作安全
     */
    private static final Map<String, Cache<String, Object>> cacheMap = new ConcurrentHashMap<>();

    /**
     * 设置为1，保证同一个服务器，只能有一个线程执行写操作(单服务器，线程安全)
     */
    private final static Integer CONCURRENCY_LEVEL = 1;
    /**
     * 设置为1，保证一个cache，只针对一个key进行缓存
     */
    private final static Integer INITIAL_CAPACITY = 1;
    /**
     * 设置为1，保证一个cache，只针对一个key进行缓存
     */
    private final static Integer MAXIMUM_SIZE = 1;

    private static void setCacheMap(String key, long expire, TimeUnit timeUnit) {
        Cache<String, Object> cache
                //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                = CacheBuilder.newBuilder()
                //设置并发级别，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(CONCURRENCY_LEVEL)
                //设置写缓存后过期时间
                .expireAfterWrite(expire, timeUnit)
                //设置缓存容器的初始容量
                .initialCapacity(INITIAL_CAPACITY)
                //设置缓存最大容量，超过最大容量之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(MAXIMUM_SIZE)
                //设置要统计缓存的命中率
                .recordStats()
                //设置缓存的移除通知
                .removalListener(new RemovalListener<String, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Object> notification) {
                        log.info("{} was removed, cause is {}", notification.getKey(), notification.getCause());
                    }
                })
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build();

        cacheMap.put(key, cache);

    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    public static void set(String key, Object value, long expire, TimeUnit timeUnit) {
        setCacheMap(key, expire, timeUnit);
        cacheMap.get(key).put(key, value);
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        Cache<String, Object> cache = cacheMap.get(key);
        if (null == cache) {
            return null;
        }
        return cache.getIfPresent(key);
    }


}
