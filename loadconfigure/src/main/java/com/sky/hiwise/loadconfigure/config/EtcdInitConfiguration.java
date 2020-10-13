package com.sky.hiwise.loadconfigure.config;

import com.sky.hiwise.loadconfigure.EtcdClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author linjunli
 * @date 2018/8/31
 */
@Configuration
@Component
public class EtcdInitConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "etcd")
    public EtcdClient mainEtcdClient() {
        EtcdClient client = new EtcdClient();
        return client;
    }


}