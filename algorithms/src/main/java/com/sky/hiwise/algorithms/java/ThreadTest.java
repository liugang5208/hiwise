package com.sky.hiwise.algorithms.java;

import java.util.concurrent.locks.LockSupport;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
            ()->{
//               while (!Thread.currentThread().isInterrupted()) {
//                    System.out.println("begin");
//                    LockSupport.park();
//                }
                while (true) {
                    System.out.println("begin");
                    LockSupport.park();
                    System.out.println("end");
                }
            }
        );
        thread.start();
        Thread.sleep(1000);

        thread.interrupt();
    }
}
