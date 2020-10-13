package com.sky.hiwise.mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/es")
@Slf4j
public class EsController {

    @Autowired
    RestHighLevelClient esClient;

    @GetMapping("/index")
    public String test1() {

        GetIndexRequest request = new GetIndexRequest("car");

        try {
            boolean exists = esClient.indices().exists(request, RequestOptions.DEFAULT);
            log.info("exists : {}", exists);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
