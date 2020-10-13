package com.sky.hiwise.design.lesson.annotation;

@ClassInfo(date = "2018.4.23", comments = "测试annotation")
public class MyTest {

    @Testable(type = "m1", source = "MyTest")
    public static void m1() {

    }

    public static void m2() {

    }

    @Testable
    public static void m3() {

        throw new IllegalArgumentException("参数错误");
    }

    public static void m4() {

    }

    @Testable
    public static void m5() {

    }

    public static void m6() {

    }

    @Testable(type = "m7", source = "MyTest")
    public static void m7() {

        throw new RuntimeException("业务逻辑错误");
    }

    public static void m8() {

    }
}
