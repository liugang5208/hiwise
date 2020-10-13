package com.sky.hiwise.design.lesson.thread;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicTest1 {

    private static AtomicIntegerFieldUpdater<AtomicTest1> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicTest1.class, "count");

    public volatile int count = 100;

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {

        AtomicTest1 example = new AtomicTest1();
        if (updater.compareAndSet(example, 100, 120)) {
            System.out.println("update success 1, " + example.getCount());
        }

        if (updater.compareAndSet(example, 100, 120)) {
            System.out.println("update success 2, " + example.getCount());
        } else {
            System.out.println("update failed, " + example.getCount());
        }
    }

}
