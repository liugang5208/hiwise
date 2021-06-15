package com.sky.hiwise.study.aop.proxy;

public class RealSubject implements Subject {

    @Override
    public void request() throws Exception {
        System.out.println("RealSubject execute request()");
    }
}
