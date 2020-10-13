package com.sky.hiwise.kafka;

import java.util.ConcurrentModificationException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class KafkaConsumerTest {

    private static final long NO_CURRENT_THREAD = -1L;
    private final AtomicLong currentThread;
    private final AtomicInteger refcount;

    public KafkaConsumerTest() {
        this.currentThread = new AtomicLong(-1L);
        this.refcount = new AtomicInteger(0);
    }

    public void subscribe(){
        this.acquire();
    }

    public void acquire() {
        long threadId = Thread.currentThread().getId();
        if (threadId != this.currentThread.get() && !this.currentThread.compareAndSet(-1L, threadId)) {
            throw new ConcurrentModificationException("KafkaConsumer is not safe for multi-threaded access");
        } else {
            this.refcount.incrementAndGet();
        }
    }
}
