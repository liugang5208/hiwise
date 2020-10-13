package com.sky.hiwise.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServiceHandler {

    @Autowired
    private List<BaseService> services;

    @Autowired
    private Map<String, BaseService> servicesHolder = new ConcurrentHashMap<>();

    public static Map<String, BaseService> context = new HashMap<>();

    @PostConstruct
    public void init() {
        if (services != null) {
            for(BaseService bs : services) {
                CustomerType type = bs.getClass().getAnnotation(CustomerType.class);
                context.putIfAbsent(type.value(), bs);
            }
        }
    }

    public static BaseService getService(String type) {
        return context.get(type);
    }

    public BaseService getServiceFromHolder(String type) {
        return servicesHolder.get(type);
    }

}
