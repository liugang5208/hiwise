package com.sky.hiwise.study;

class ThreadTest implements Runnable {

    public ThreadTest() {

    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class Test {
    public static void main(String[] args) {
        Object o = new Object();
        Thread t = new Thread(new ThreadTest());
        System.gc();
        printMemory();


    }



    private static void printMemory() {
        //当前JVM占用的内存总数(M)
        double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);

        //JVM最大可用内存总数(M)

        double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);

        //JVM空闲内存(M)

        double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);

        //可用内存内存(M)
        double mayuse=(max - total + free);

        //已经使用内存(M)

        double used=(total-free);
        System.out.println("max:" + max + " used:" + used + " mayuse:" + mayuse);
    }
}
