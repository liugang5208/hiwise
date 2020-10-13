package com.sky.hiwise.test.service;

import com.sky.hiwise.test.annotation.DebugLog;

public interface UserService {

    void test();

    @DebugLog("test1")
    default void test1() {
        System.out.println("======test1=====");
    }

    default void filter1() {
        System.out.println("=====filter======");
    }
}
