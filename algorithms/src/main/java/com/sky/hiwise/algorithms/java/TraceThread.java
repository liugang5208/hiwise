package com.sky.hiwise.algorithms.java;

import java.util.UUID;
import java.util.concurrent.*;

public class TraceThread {

    private static ThreadLocal<String> trace = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        String traceId = "";//UUID.randomUUID().toString().replace("-", "");
        //trace.set(traceId);
       // trace.set(UUID.randomUUID().toString().replace("-", ""));
//        System.out.println(trace.get());
//        new Thread(() -> {
//            System.out.println(trace.get());
//        }).start();
        ExecutorService service = new ThreadPoolExecutor(5, 10, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        //service.submit(new TransRunnable(new TestRun(), traceId));
        for (int i = 0; i < 10; i++) {
            traceId = "traceId" + i + ":" + UUID.randomUUID().toString().replace("-", "");
            ThreadContext.setServerContext(traceId);
            service.submit(new TransRunnable(new TestRun(), traceId));
        }
        service.shutdown();
    }

    public static class TestRun implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("TestRun:" + ThreadContext.getServerContext());
            } finally {
                ThreadContext.removeServerContext();
            }
        }
    }

    public static class TransRunnable implements Runnable {

        //private static ThreadLocal<String> trace = new InheritableThreadLocal<>();
        private String traceId;
        private Runnable runnable;
        TransRunnable(Runnable runnable, String traceId) {
            this.runnable = runnable;
            //ThreadContext.setServerContext(traceId);
            this.traceId = traceId;
        }

        @Override
        public void run() {
            System.out.println("TransRunnable:" + ThreadContext.getServerContext());
            ThreadContext.setServerContext(traceId);
            runnable.run();
        }
    }

}
