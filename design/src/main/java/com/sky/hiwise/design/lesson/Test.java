package com.sky.hiwise.design.lesson;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class Test {

    public static void main(String[] args) {
        //CountDownLatch
        //AbstractQueuedSynchronizer
        //ReentrantReadWriteLock
        //StampedLock

        //BlockingQueue
        //ArrayBlockingQueue
        //DelayQueue
        //LinkedBlockingQueue
        //PriorityBlockingQueue  //必须实现 Comparate接口
        //SynchronousQueue
        //ThreadPoolExecutor
        int a = 100;
        Integer b = new Integer(100);
        System.out.println((a==b));
        System.out.println(b.equals(a));


        Integer c = 100;//300;//new Integer(300);
        Integer d = 100;//300;//new Integer(300);
        System.out.println((c==d));
        System.out.println(d.equals(c));
    }

    private void test() {
    }
    public void test1() {
    }
}

class Test1 extends Test {
    public void test() {
    }

}
