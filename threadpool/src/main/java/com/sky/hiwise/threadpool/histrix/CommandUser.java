package com.sky.hiwise.threadpool.histrix;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CommandUser extends HystrixCommand<String> {

    private String userName;

    private RestTemplate restTemplate;

    public CommandUser(String userName, RestTemplate restTemplate) {


        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserPool"))

                //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000))

                //线程池隔离
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                                .withExecutionTimeoutInMilliseconds(6000)
                )
        )
        ;
        this.userName = userName;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        log.info("userName=[{}]", userName);

        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity("http://127.0.0.1:8088/test2", String.class);
        String body2 = responseEntity2.getBody();
        return "userName=" + userName + ";body:" + body2;
    }
}
