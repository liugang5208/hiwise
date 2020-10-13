package com.sky.hiwise.lessons.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 **/
@Repository("redisDao")
public class RedisDaoImpl implements RedisDao{
    @Resource
    protected HashOperations<String,String,Object> hashOperations;

    @Resource
    protected RedisTemplate<String,Object> redisTemplate;

    @Resource
    protected ValueOperations<String, Object> valueOperations;




    /**
     * 查询key,支持模糊查询
     *
     * @param key 传过来时key的前后端已经加入了*，或者根据具体处理
     * */
    @Override
    public Set<String> keys(String key){
        return redisTemplate.keys(key);
    }

    /**
     * 重命名key
     * */
    @Override
    public void renameKey(String key,String newKey){
        redisTemplate.rename(key,newKey);
    }


    /**
     *字符串添加信息
     * @param key
     * @param obj 可以是单个的值，也可以是任意类型的对象
     * */
    @Override
    public void set(String key,Object obj){
        valueOperations.set(key,obj);
    }

    /**
     *字符串添加信息
     * @param key
     * @param obj 可以是单个的值，也可以是任意类型的对象
     * @param expire 设置失效时间
     * */
    @Override
    public void set(String key,Object obj,long expire){
        valueOperations.set(key,obj,expire,TimeUnit.SECONDS);
    }

    /**
     * 字符串获取值
     * @param key
     * */
    @Override
    public Object get(String key){
        return valueOperations.get(key);
    }

    /**
     * 删出key
     * 这里跟下边deleteKey（）最底层实现都是一样的，应该可以通用
     * @param key
     * */
    @Override
    public void delete(String key){
        valueOperations.getOperations().delete(key);
    }

    /**
     * 添加单个
     *
     * @param key    key
     * @param filed  filed
     * @param domain 对象
     */
    @Override
    public void hset(String key,String filed,Object domain){
        hashOperations.put(key, filed, domain);
    }


    /**
     * 添加HashMap
     *
     * @param key    key
     * @param hm    要存入的hash表
     */
    @Override
    public void hmset(String key, HashMap<String,Object> hm){
        hashOperations.putAll(key,hm);
    }

    /**
     * 查询key和field所确定的值
     *
     * @param key 查询的key
     * @param field 查询的field
     * @return HV
     */
    @Override
    public Object hget(String key,String field) {
        return hashOperations.get(key, field);
    }

    /**
     * 查询该key下所有值
     *
     * @param key 查询的key
     * @return Map<HK, HV>
     */
    @Override
    public Object hget(String key) {
        return hashOperations.entries(key);
    }

    /**
     * 删除key下所有值
     *
     * @param key 查询的key
     */
    @Override
    public void deleteKey(String key) {
        hashOperations.getOperations().delete(key);
    }

    /**
     * 判断key和field下是否有值
     *
     * @param key 判断的key
     * @param field 判断的field
     */
    @Override
    public Boolean hasKey(String key,String field) {
        return hashOperations.hasKey(key,field);
    }

    /**
     * 判断key下是否有值
     *
     * @param key 判断的key
     */
    @Override
    public Boolean hasKey(String key) {
        return hashOperations.getOperations().hasKey(key);
    }

    @Override
    public Boolean expire(String key, long sec) {
        return redisTemplate.expire(key,sec,TimeUnit.SECONDS);
    }

    @Override
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key,date);
    }
}
