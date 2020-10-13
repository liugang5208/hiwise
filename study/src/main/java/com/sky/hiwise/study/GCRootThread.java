package com.sky.hiwise.study;

public class GCRootThread {

    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) throws Exception {
        System.out.println("开始前内存情况：");
        printMemory();

        AsyncTask at = new AsyncTask(new GCRootThread());
        Thread thread = new Thread(at);
        thread.start();

        // 第一次尝试 GC，打印内存占用
        System.gc();
        System.out.println("main 方法执行完毕，完成 GC");
        printMemory();

        // 强制等待活跃线程执行完毕
        thread.join();
        at = null;


        // 第二次尝试 GC，打印内存占用
        System.gc();
        System.out.println("线程代码执行完毕，完成 GC");
        printMemory();
    }

    private static void printMemory() {
        long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        long total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        System.out.printf("free is %d M, total is %d M \n", free, total);
    }


    private static class AsyncTask implements Runnable {

        // 验证：激活状态的线程是不会被 GC 回收的，所以它持有的对象也不会被回收。
        GCRootThread gcRootThread;

        AsyncTask(GCRootThread gcRootThread) {
            this.gcRootThread = gcRootThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // no-op
            }
        }
    }

}
