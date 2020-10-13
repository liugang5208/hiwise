package com.sky.hiwise.kafka;

public class Test1 {

    public static void main(String[] args) {

        KafkaConsumerTest test1 = new KafkaConsumerTest();
        (new Thread(()->{
            System.out.println("test1");
            test1.subscribe();
        })).start();
        (new Thread(()->{
            System.out.println("test2");
            test1.subscribe();
        })).start();


    }
}
