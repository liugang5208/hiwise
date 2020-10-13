package com.sky.hiwise.design.lesson.basic;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringDemo {

    public static void main(String[] args) {
        //StringUtils.substringBefore("Start");
        //test();
        //test1();
//        test2();
//        test3();
//        test4();
    }

    public static void test() {
        String s1 = "test";
        String s2 = "test";
        System.out.println(s1.equals(s2));

        String s3 = new String("test");
        String s4 = new String("test");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));

        char [] s = {'a','b','c'};
        String s5 = new String(s);
        System.out.println(s5);
        String s6 = "test";
        String s7 = "test1";
        System.out.println( s6.compareTo(s7));
        String s8 = null;
        if (s8.equals("111")) {
            System.out.println(1111);
        } else {
            System.out.println(12345);
        }
//        compareTo() 的返回值是int, 它是先比较对应字符的大小(ASCII码顺序)
//        1、如果字符串相等返回值0
//        2、如果第一个字符和参数的第一个字符不等,结束比较,返回他们之间的差值（ascii码值）（负值前字符串的值小于后字符串，正值前字符串大于后字符串）
//        3、如果第一个字符和参数的第一个字符相等,则以第二个字符和参数的第二个字符做比较,以此类推,直至比较的字符或被比较的字符有一方全比较完,这时就比较字符的长度.
    }

    public static void test1() {

        String S1 = "This is only a" + " simple" + " test";
        StringBuffer Sb1 = new StringBuffer("This is only a").append(" simple").append(" test");

        String s6 = Sb1.toString() + "test";

        String S2 = "This is only a";
        String S3 = " simple";
        String S4 = " test";
        String S5 = S2 +S3 + S4;
    }

    public static void test2() {
        String tempStr = "a";
        int times = 5000;
        long start = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < times; i++) {
            str += tempStr;
        }
        long end = System.currentTimeMillis();
        long time = (end - start);
        System.out.println(time);
    }

    public static void test3() {
        String tempStr = "a";
        int times = 50000;
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < times; i++) {
            sb.append(tempStr);
        }
        long end = System.currentTimeMillis();
        long time3 = (end - start);
        System.out.println(time3);
    }

    public static void test4() {
        String tempStr = "a";
        int times = 50000;
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(tempStr);
        }
        long end = System.currentTimeMillis();
        long time4 = (end - start);
        System.out.println(time4);
    }

}
