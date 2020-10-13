package com.sky.hiwise.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.sky.hiwise.disruptor.netty.DisruptorServer;
import com.sky.hiwise.disruptor.server.MessageConsumer;
import com.sky.hiwise.disruptor.server.MessageConsumerImpl4Server;
import com.sky.hiwise.disruptor.server.RingBufferWorkerPoolFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DisruptorBoot implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("DisruptorBoot onApplicationEvent");
        DisruptorServer.getInstance().start();

        /**
         * 多生产 多消费模式
         */
        MessageConsumer[] conusmers = new MessageConsumer[4];
        for(int i =0; i < conusmers.length; i++) {
            MessageConsumer messageConsumer = new MessageConsumerImpl4Server("code:serverId:" + i);
            conusmers[i] = messageConsumer;
        }
        RingBufferWorkerPoolFactory.getInstance().initAndStart(ProducerType.MULTI,
                1024*1024,
                //new YieldingWaitStrategy(),
                new BlockingWaitStrategy(),
                conusmers);
    }
}
