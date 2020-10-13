package com.sky.hiwise.lessons.consumer;//package com.sky.lesson.consumer;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class KafkaConsumer implements CommandLineRunner {
//
//    @Autowired
//    private KafkaTemplate<String, String> template;
//
//    private final CountDownLatch latch = new CountDownLatch(3);
//
//    @Override
//    public void run(String... args) throws Exception {
//        this.template.send("test", "foo1");
//        this.template.send("test", "foo2");
//        this.template.send("test", "foo3");
//        latch.await(60, TimeUnit.SECONDS);
//        System.out.println("All send");
//    }
//
//    @KafkaListener(topics = "test", groupId = "test")
//    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
//        System.out.println(cr.toString());
//        latch.countDown();
//    }
//}
