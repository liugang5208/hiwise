package com.sky.hiwise.study.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class ResolverConfig extends WebMvcConfigurerAdapter {

    /**
     * 添加参数解析，将参数的形式从下划线转化为驼峰
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new UnderlineToCamelArgumentResolver());
    }
}
