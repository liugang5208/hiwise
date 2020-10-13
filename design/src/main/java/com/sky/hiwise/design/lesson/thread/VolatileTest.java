package com.sky.hiwise.design.lesson.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {

    /**
     *volatile使用场景
     * 对变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值
     * 不满足 number++ count = count * 5
     * 满足 boolean变量
     * 该变量没有包含在具有其他变量的不变式中 （不满足 low < up）
     */
    private volatile int number = 0;

    private Lock lock = new ReentrantLock();
    public int getNumber() {
        return number;
    }

    public void increase() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            this.number++;
        } finally {
            lock.unlock();
        }
       // this.number++;
//        synchronized(this) {
//            this.number++;
//        }
    }

    /**
     * volatile和synchronized的区别
     1. volatile本质是在告诉jvm当前变量在寄存器（工作内存）中的值是不确定的，需要从主存中读取； synchronized则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住。
     2. volatile仅能使用在变量级别；synchronized则可以使用在变量、方法、和类级别的
     3. volatile仅能实现变量的修改可见性，不能保证原子性；而synchronized则可以保证变量的修改可见性和原子性
     4. volatile不会造成线程的阻塞；synchronized可能会造成线程的阻塞。
     5. volatile标记的变量不会被编译器优化；synchronized标记的变量可以被编译器优化
     * @param args
     */
    public static void main(String[] args) {
        final VolatileTest vt = new VolatileTest();
        for (int i = 0; i < 500; i++) {
            new Thread(()->{
                vt.increase();
            }).start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println("number:" + vt.getNumber());
    }
}


/**
 * 结论：
 volatile进行加操作线程不安全的，不适合计数场景
 volatile关键字不具有原子性
 使用场景
 ​ 使用volatile必须具备两个条件
 对变量的写操作，不依赖于当前值
 该变量没有包含在具有其他变量的不变式子中
 因此volatile适合作为状态的标记量
 */
