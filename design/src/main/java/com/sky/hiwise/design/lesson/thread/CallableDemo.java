package com.sky.hiwise.design.lesson.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    public static void main(String[] args) {

        CallableDemo callableDemo = new CallableDemo();

        //创建Callable接口的实现类 实现call方法 且有返回值
        //用FutureTask封装Callable对象 该FutureTask对象封装了Callable对象的call的返回值
        //用FutureTask对象作为Thread target创建并启动新线程
        //用FutureTask对象的get 方法获取子线程结束后的返回值
        FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>)()->{
            int i = 0;
            for(; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " task中i的值为：" + i);
            }
            return i;
        });

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " i的值为：" + i);
            if (i == 20) {
                new Thread(task, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值" + task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
