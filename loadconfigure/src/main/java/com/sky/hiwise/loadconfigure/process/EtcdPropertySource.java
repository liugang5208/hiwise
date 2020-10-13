package com.sky.hiwise.loadconfigure.process;

import org.springframework.core.env.EnumerablePropertySource;

import java.util.Map;

/**
 * @author linjunli
 * @date 2018/9/3
 */
public class EtcdPropertySource extends EnumerablePropertySource<Map<String, Object>> {

    public EtcdPropertySource(String name, Map source) {
        super(name, source);
    }

    /**
     * 获取所有的配置名字
     */
    @Override
    public String[] getPropertyNames() {
        return source.keySet().toArray(new String[source.size()]);
    }

    /**
     * 根据配置返回对应的属性
     *
     * @param name
     * @return
     */
    @Override
    public Object getProperty(String name) {
        return source.get(name);
    }
}
