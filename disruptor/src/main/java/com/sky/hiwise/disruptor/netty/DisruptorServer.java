package com.sky.hiwise.disruptor.netty;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.sky.hiwise.disruptor.demo.LongEvent;
import com.sky.hiwise.disruptor.demo.LongEventFactory;
import com.sky.hiwise.disruptor.demo.LongEventHandler;
import lombok.Data;

@Data
public class DisruptorServer {

    private static class SingletonDisruptorServer {
        static final DisruptorServer instance = new DisruptorServer();
    }

    public static DisruptorServer getInstance() {
        return SingletonDisruptorServer.instance;
    }

    private  Disruptor<LongEvent> disruptor;

    private DisruptorServer() {
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);
        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());
    }

    public void start() {
        disruptor.start();
        System.out.println("disruptor start...");
    }

}
