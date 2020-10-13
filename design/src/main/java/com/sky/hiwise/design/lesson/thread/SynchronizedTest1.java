package com.sky.hiwise.design.lesson.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized : 关键字，并且依赖与JVM，作用对象的作用范围内都是同一时刻只能有一个线程对其操作的
 Lock : 接口类，依赖特殊的CPU指定，使用代码实现，常用子类ReentrantLock
 synchronized
 修饰代码块：大括号括起来的代码，也称同步代码块，作用与调用的对象
 修饰方法：整个方法，也称同步方法，作用与调用的对象
 修饰静态方法：整个静态方法，作用于类的所有对象
 修饰类：括号括起来的部分，作用与类的所有对象
 */
public class SynchronizedTest1 {

    // 修饰一个代码块
    public void test1(int j) {
        //同步代码块 作用于调用的对象
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("test1 " + j + ":" + i);
            }
        }
    }

    // 修饰一个方法 同步方法 作用于调用的对象
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            System.out.println("test2 " + j + ":" + i);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest1 example1 = new SynchronizedTest1();
        SynchronizedTest1 example2 = new SynchronizedTest1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example1.test2(1);
            //example2.test2(2);
        });
    }

}

/**
 * 注意：如果某个类为父类，并且存在同步方法，子类在继承这个类后，如果子类调用该父类的同步方法后，
 * 该方法是没有synchronized关键字的，原因是synchronized不属于方法声明的一部分
  对比
 synchronized ：不可中断锁，适合竞争不激烈，可读性较好
 Lock：可中断锁，多样化同步，竞争激烈时能维持常态
 Atomic：竞争激烈时能维持常态，比Lock性能好；只能同步一个值
 */
