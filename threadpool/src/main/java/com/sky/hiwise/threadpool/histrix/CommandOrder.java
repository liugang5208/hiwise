package com.sky.hiwise.threadpool.histrix;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CommandOrder extends HystrixCommand<String> {

    private String orderName;

    private RestTemplate restTemplate;

    public CommandOrder(String orderName, RestTemplate restTemplate) {


        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey("OrderGroup"))
                //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("OrderPool"))

                //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000))

                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                                .withExecutionTimeoutInMilliseconds(6000)
                )
        )
        ;
        this.orderName = orderName;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        log.info("orderName=[{}]", orderName);
        ResponseEntity<String> responseEntity1 = restTemplate.getForEntity("http://127.0.0.1:8088/test1", String.class);
        String body1 = responseEntity1.getBody();
        return "OrderName=" + orderName + ";body:" + body1;
    }
}
