package com.sky.hiwise.disruptor.demo;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {
        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);
        final long startTime = System.currentTimeMillis();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l <= 50; l++)
        {
            bb.putLong(0, l);
            producer.onData(bb);
            //Thread.sleep(1000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("disruptor costTime = " + (endTime - startTime) + "ms");

    }

//    public static void main(String[] args) throws Exception
//    {
//        // Specify the size of the ring buffer, must be power of 2.
//        int bufferSize = 1024;
//
//        // Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
//
//        // Connect the handler
//        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event));
//
//        // Start the Disruptor, starts all threads running
//        disruptor.start();
//
//        // Get the ring buffer from the Disruptor to be used for publishing.
//        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        for (long l = 0; true; l++)
//        {
//            bb.putLong(0, l);
//            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
//            Thread.sleep(1000);
//        }
//    }
}

