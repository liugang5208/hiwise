package com.sky.hiwise.algorithms.leetcode.interview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class StorageQueue {

    private final static int MAX_SIZE = 20;

    Queue<String> storage;
    public StorageQueue() {
        storage = new LinkedList<>();
    }

    public synchronized void put(String value, String threadName) {
        while (storage.size() >= MAX_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(threadName + ":" + value);
        System.out.println(threadName + ":" + value);
        notify();
    }

    public synchronized String get(String threadName) {
        while (storage.isEmpty()) {
            try {
                wait();
                System.out.println(threadName + " : wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notify();
        String value = storage.remove();
        return value;
    }

    public static void main(String[] args) {
        StorageQueue queue = new StorageQueue();
        (new Thread(()->{
            long oldTime = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - oldTime >= 1000) {
                    try {
                        String uid = UUID.randomUUID().toString();
                        queue.put(uid, Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        })).start();
        (new Thread(()->{
            while (true) {
                String data = queue.get(Thread.currentThread().getName());
                if (data != null) {
                    System.out.println(Thread.currentThread().getName() + " consumer data : " + data);
                }
            }
        })).start();
    }
}
