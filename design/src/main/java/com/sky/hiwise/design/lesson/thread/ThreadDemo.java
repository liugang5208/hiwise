package com.sky.hiwise.design.lesson.thread;

class Thread1 extends Thread {

    private int count = 5;
    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    public Thread1(ThreadGroup group, String name) {
        super(group, name);
        this.name = name;
    }

    @Override
    public void run() {
        while(count > 0) {
            count --;
            System.out.println(name + "卖了1张票，剩余票数为：" + count);
        }

    }
}

class Thread2 implements Runnable {

    private int count = 5;

    public void run() {
        while(count > 0) {
            count --;
            System.out.println(Thread.currentThread().getName() + "卖了1张票，剩余票数为：" + count);
        }
    }
}

public class ThreadDemo {

    /**
     * Runnable方式可以避免Thread方式由于Java单继承特性带来的缺陷
     * 可以被多个线程（Thread实例）共享，适合于多个线程处理同一资源的情况
     *
     * 就绪：创建了线程对象后，调用了线程的start方法，此时线程只是进入了线程队列，等待获取cpu服务，具备了运行条件，但并不一定已经开始运行
     * 运行：处于就绪状态的线程，一旦获取CPU的资源，便进入到运行状态，开始执行run方法里面的逻辑
     * 终止：线程的run方法执行完毕，或者线程调用了stop方法 线程便进入终止状态
     * 阻塞：一个正在执行的线程在某些情况下，由于某种原因暂时让出CPU资源，暂停了自己的执行，便进入了阻塞状态，如调用了sleep（）方法
     * @param args
     */
    public static void main(String[] args) {
        Thread1 mt1 = new Thread1("窗口1");
        Thread1 mt2 = new Thread1("窗口2");
        Thread1 mt3 = new Thread1("窗口3");

//        mt1.start();
//        mt2.start();
//        mt3.start();
//        Thread2 mt2 = new Thread2();
//        Thread th1 = new Thread(mt2, "窗口1");
//        Thread th2 = new Thread(mt2, "窗口2");
//        Thread th3 = new Thread(mt2, "窗口3");
//
//        th1.start();
//        th2.start();
//        th3.start();

        //testGroup();
    }

    public static void testGroup() {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组名字：" + mainGroup);
        System.out.println("主线程是否是后台线程组：" +  mainGroup.isDaemon());
        new Thread1("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        System.out.println("tg线程是否是后台线程组：" + tg.isDaemon());
        new Thread1(tg, "tg线程组的线程1").start();
        new Thread1(tg, "tg线程组的线程2").start();

    }

}
