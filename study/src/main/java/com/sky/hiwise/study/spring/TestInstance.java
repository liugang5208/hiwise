package com.sky.hiwise.study.spring;

public class TestInstance {

    public static void test() {
        System.out.println("test");
    }

    private static class LazyHandler {
        private static final TestInstance pool = new TestInstance();
    }

    private TestInstance() {
        System.out.println("TestInstance");
    }

    public static TestInstance getInstance() {
        return LazyHandler.pool;
    }

    public static void main(String[] args) {
        System.out.println(1111);
        test();
    }
}
