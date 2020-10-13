package com.sky.hiwise.study.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> test = MyApplicationContextAware.getApplicationContext().getBeansWithAnnotation(CustomerType.class);
        test.forEach((k, v) -> {
            //Class<BaseService> baseServiceClass = (Class<BaseService>) v.getClass();
            String type = v.getClass().getAnnotation(CustomerType.class).value();
            System.out.println(type);
        });
        System.out.println(test);
        //Map<String, Object> test = applicationContext.getBeansWithAnnotation(CustomerType.class);

    }
}
