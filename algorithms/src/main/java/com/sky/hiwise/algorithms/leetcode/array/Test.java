package com.sky.hiwise.algorithms.leetcode.array;

import java.sql.DriverManager;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(Integer.MAX_VALUE);
        System.out.println(atomicInteger.incrementAndGet());

        String s = "abcabc";

        System.out.println((s + s).indexOf(s, 1) != s.length());

    }
}