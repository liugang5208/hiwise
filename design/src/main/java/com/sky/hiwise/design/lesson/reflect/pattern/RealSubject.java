package com.sky.hiwise.design.lesson.reflect.pattern;

/**
 */
public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("real subject execute request");
    }

    @Override
    public void hello() {
        System.out.println("hello");
    }
}
