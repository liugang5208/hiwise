package com.sky.hiwise.lessons.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public interface RedisDao {

    /**
     * 查询key,支持模糊查询
     *
     * @param key 传过来时key的前后端已经加入了*，或者根据具体处理
     * */
     Set<String> keys(String key);

    /**
     * 重命名key
     * */
     void renameKey(String key, String newKey);


    /**
     *字符串添加信息
     * @param key
     * @param obj 可以是单个的值，也可以是任意类型的对象
     * */
     void set(String key, Object obj);

    /**
     *字符串添加信息
     * @param key
     * @param obj 可以是单个的值，也可以是任意类型的对象
     * @param expire 设置失效时间
     * */
     void set(String key, Object obj, long expire);

    /**
     * 字符串获取值
     * @param key
     * */
     Object get(String key);

    /**
     * 删出key
     * 这里跟下边deleteKey（）最底层实现都是一样的，应该可以通用
     * @param key
     * */
     void delete(String key);

    /**
     * 添加单个
     *
     * @param key    key
     * @param filed  filed
     * @param domain 对象
     */
     void hset(String key, String filed, Object domain);


    /**
     * 添加HashMap
     *
     * @param key    key
     * @param hm    要存入的hash表
     */
     void hmset(String key, HashMap<String, Object> hm);

    /**
     * 查询key和field所确定的值
     *
     * @param key 查询的key
     * @param field 查询的field
     * @return HV
     */
     Object hget(String key, String field);

    /**
     * 查询该key下所有值
     *
     * @param key 查询的key
     * @return Map<HK, HV>
     */
     Object hget(String key);

    /**
     * 删除key下所有值
     *
     * @param key 查询的key
     */
     void deleteKey(String key);

    /**
     * 判断key和field下是否有值
     *
     * @param key 判断的key
     * @param field 判断的field
     */
     Boolean hasKey(String key, String field);

    /**
     * 判断key下是否有值
     *
     * @param key 判断的key
     */
     Boolean hasKey(String key);

    Boolean expire(String key, long sec);

    Boolean expireAt(String key, Date date);

}
