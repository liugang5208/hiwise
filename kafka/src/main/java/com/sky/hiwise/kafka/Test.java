package com.sky.hiwise.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class Test {

    @Autowired
    private KafkaTemplate<String, String> template;

    private final CountDownLatch latch = new CountDownLatch(3);

//    @Override
//    public void run(String... args) throws Exception {
//
//        this.template.send("ws_org_staff_leave_beta", "foo1");
//        this.template.send("ws_org_staff_leave_beta", "foo2");
//        this.template.send("ws_org_staff_leave_beta", "foo3");
//        latch.await(60, TimeUnit.SECONDS);
//        log.info("All received");
//    }

    @KafkaListener(topics = "ws_org_staff_leave_beta")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        log.info(cr.toString());

        //latch.countDown();
    }
}
