package com.sky.hiwise.loadconfigure.config;

import com.sky.hiwise.loadconfigure.EtcdClient;
import com.sky.hiwise.loadconfigure.process.EtcdPropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linjunli
 * @date 2018/8/31
 */
@Configuration
public class EtcdConfiguration {
    @Bean
    public EtcdClient etcdClient() {
        return new EtcdClient();
    }

    @Bean
    public EtcdPropertySourceLocator myPropertySourceLocator() {
        return new EtcdPropertySourceLocator(etcdClient());
    }

}