package com.sky.hiwise.design.lesson.thread;

import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {

        //testPool();
        //testRecursiveAction();
        testRecursiveTask();
    }



    public static void testPool() {

        //调用Executors类静态工厂方法创建一个ExecutorService对象，该对象代表一个线程池
        //创建Runnable实现类 或者Callable实现类的实例 作为线程执行任务
        //调用ExecutorService对象的submit方法提交 Runnable实现类 或者Callable实例
        //调用ExecutorService对象的 shutdown 关闭线程池 注意与shutdownNow区别
        ExecutorService pool = Executors.newFixedThreadPool(6);

        Runnable target = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " i的值：" + i);
            }
        };

        pool.submit(target);
        pool.submit(target);

        pool.shutdown();
    }

    public static void testRecursiveAction() {
        try {
            ForkJoinPool pool = new ForkJoinPool();

            pool.submit(new PrintTask(0, 300));

            pool.awaitTermination(2, TimeUnit.SECONDS);

            pool.shutdown();
        } catch (InterruptedException e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public static void testRecursiveTask() {
        try {
            int arr[] = new int[100];

            Random random = new Random();

            int total = 0;

            for (int i = 0; i < arr.length; i++) {
                int tmp = random.nextInt(20);
                total += (arr[i] = tmp);

            }
            System.out.println(total);
            ForkJoinPool pool = ForkJoinPool.commonPool();

            Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));

            System.out.println(future.get());
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class PrintTask extends RecursiveAction {

    private static final int LIMIT = 50;
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {

        if (end - start < LIMIT) {
            for (int i = start; i < end; i++){
                System.out.println(Thread.currentThread().getName() + " i的值" + i);
            }
        } else {
            int mid = (start + end) / 2;
            PrintTask left = new PrintTask(start, mid);
            PrintTask right = new PrintTask(mid, end);
            left.fork();
            right.fork();
        }

    }
}

class CalTask extends RecursiveTask<Integer> {


    private static final int LIMIT = 20;
    private int start;
    private int end;
    private int arr[];

    public CalTask(int arr[], int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < LIMIT) {
            for (int i = start; i < end; i++){
                sum += arr[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            CalTask left = new CalTask(arr, start, mid);
            CalTask right = new CalTask(arr, mid, end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}

/**
 * ThreadPoolExecutor、AbstractExecutorService、ExecutorService和Executor几个之间的关系。
 Executor是一个顶层接口，在它里面只声明了一个方法execute(Runnable)，返回值为void，
 参数为Runnable类型，从字面意思可以理解，就是用来执行传进去的任务的；

 然后ExecutorService接口继承了Executor接口，并声明了一些方法：submit、invokeAll、invokeAny以及shutDown等；

 抽象类AbstractExecutorService实现了ExecutorService接口，基本实现了ExecutorService中声明的所有方法；

 然后ThreadPoolExecutor继承了类AbstractExecutorService。

 在ThreadPoolExecutor类中有几个非常重要的方法：
 execute()
 submit()
 shutdown()
 shutdownNow()
 execute()方法实际上是Executor中声明的方法，在ThreadPoolExecutor进行了具体的实现，
 这个方法是ThreadPoolExecutor的核心方法，通过这个方法可以向线程池提交一个任务，交由线程池去执行。

 submit()方法是在ExecutorService中声明的方法，在AbstractExecutorService就已经有了具体的实现，
 在ThreadPoolExecutor中并没有对其进行重写，这个方法也是用来向线程池提交任务的，
 但是它和execute()方法不同，它能够返回任务执行的结果，去看submit()方法的实现，会发现它实际上还是调用的execute()方法，
 只不过它利用了Future来获取任务执行结果）。

 shutdown()和shutdownNow()是用来关闭线程池的。
 还有很多其他的方法：
 比如：getQueue() 、getPoolSize() 、getActiveCount()、getCompletedTaskCount()等获取与线程池相关属性的方法，自行查阅API。
 */
