package com.sky.hiwise.algorithms.java;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        int[] a = new int[]{5,0,9,1,20,1,8};
        int[] b = a.clone();
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        //test();
        //student name code sex
        //score  code math
        /**
         * select
         * st.name, st.code, sc.math
         * from student as st left join score as sc on st.code = sc.code
         * where st.sex = 1
         */
    }

    public static Thread t1, t2;
    public volatile int count = 0;
    public void test2() {
        t1 = new Thread(()->{
            while (true) {
                count ++;
                if (count > 10) {
                    break;
                }
                LockSupport.park();
                System.out.println("AAAA");
                LockSupport.unpark(t2);
            }
        });
        t1.start();
        t2 = new Thread(()->{
            while (true) {
                if (count > 10) {
                    break;
                }
                LockSupport.unpark(t1);
                System.out.println("BBBB");
                LockSupport.park();
            }
        });
        t2.start();

    }

    public void test1() {
        Object object = new Object();
        Thread t = new Thread(()->{
            synchronized (object) {
                while (true) {
                    try {
                        object.wait();
                        System.out.println("AAAA");
                        object.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        t.start();
        Thread t1 = new Thread(()->{
            synchronized (object) {
                while (true) {
                    try {
                        object.notify();
                        System.out.println("BBBBB");
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }

    public static void test() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t = new Thread(()->{
            lock.lock();
            try {
                while (true) {
                    condition.await();
                    System.out.println("AAAA");
                    condition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t.start();
        Thread t1 = new Thread(()->{
            lock.lock();
            try {
                while(true){
                    condition.signal();
                    System.out.println("bbbb");
                    condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
    }
}
