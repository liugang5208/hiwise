package com.sky.hiwise.design.lesson.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class AtomicTest {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    /**
     * AtomicReference
     AtomicReference类提供了一种读和写都是原子性的对象引用变量。
     原子意味着多个线程试图改变同一个AtomicReference(例如比较和交换操作)将不会使得AtomicReference处于不一致的状态。
     AtomicReferenc的compareAndSet()方法可以使得它与期望的一个值进行比较，如果他们是相等的，
     AtomicReference里的对象会被设置成一个新的引用。
     */
    //原子操作AtomicLong LongAdder AtomicReference  count.compareAndSet(0, 2); // 2
    public static AtomicInteger count = new AtomicInteger(0);

    /**
     * AtomicBoolean
     * AtomicBoolean是java.util.concurrent.atomic包下的原子变量，这个包里面提供了一组原子类。
     * 其基本的特性就是在多线程环境下，当有多个线程同时执行这些类的实例包含的方法时，具有排他性，
     * 即当某个线程进入方法，执行其中的指令时，不会被其他线程打断，而别的线程就像自旋锁一样，
     * 一直等到该方法执行完成，才由JVM从等待队列中选择一个另一个线程进入，这只是一种逻辑上的理解。
     * 实际上是借助硬件的相关指令来实现的，不会阻塞线程(或者说只是在硬件级别上阻塞了)。
     例如AtomicBoolean，在这个Boolean值的变化的时候不允许在之间插入，保持操作的原子性。
     方法和举例：compareAndSet(boolean expect, boolean update)。
     这个方法主要两个作用
     1. 比较AtomicBoolean和expect的值，如果一致，执行方法内的语句。其实就是一个if语句
     2. 把AtomicBoolean的值设成update
     比较最要的是这两件事是一气呵成的，这连个动作之间不会被打断，
     任何内部或者外部的语句都不可能在两个动作之间运行。为多线程的控制提供了解决的方案。
     */

    public static Integer count1 = 0;

    /**
     * https://www.jianshu.com/p/1ec1009ebab7
     *  java.util.concurrent.CountDownLatch
     一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
     用给定的计数 初始化 CountDownLatch。由于调用了countDown() 方法，所以在当前计数到达零之前，
     await 方法会一直受阻塞。到达0之后，会释放所有等待的线程，await 的所有后续调用都将立即返回。
     最常见的使用场景: 等待其他线程处理完才继续当前线程。
     Semaphore又称信号量，是操作系统中的一个概念，在Java并发编程中，信号量控制的是线程并发的数量。
     public Semaphore(int permits)
     其中参数permits就是允许同时运行的线程数目;
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:" + count1);
        System.out.println("count:" + count.get());
    }

    private static void add() {

        count.incrementAndGet();
        // count.getAndIncrement();

        count1++;
    }
}
