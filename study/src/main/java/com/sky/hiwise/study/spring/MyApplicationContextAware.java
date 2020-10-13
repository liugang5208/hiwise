package com.sky.hiwise.study.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyApplicationContextAware implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    public Map<String, Class<BaseService>> context = new HashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, Object> test = applicationContext.getBeansWithAnnotation(CustomerType.class);
        test.forEach((k, v) -> {
            Class<BaseService> baseServiceClass = (Class<BaseService>) v.getClass();
            String type = baseServiceClass.getAnnotation(CustomerType.class).value();
            context.putIfAbsent(type, baseServiceClass);
        });
        Class<BaseService> aaa  = context.get("car");
        System.out.println("MyApplicationContextAware:" + applicationContext.getBean(aaa));

        //System.out.println("MyApplicationContextAware::setApplicationContext:" + test);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
