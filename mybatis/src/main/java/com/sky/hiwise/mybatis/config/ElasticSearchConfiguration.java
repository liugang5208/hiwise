package com.sky.hiwise.mybatis.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {

    @Bean(name = "esClient")
    public RestHighLevelClient highLevelClient() {
        HttpHost httpHost = HttpHost.create("http://es.sky.com");//new HttpHost("http://es.sky.com");
        return new RestHighLevelClient(RestClient.builder(new HttpHost[]{httpHost}));
    }

}
