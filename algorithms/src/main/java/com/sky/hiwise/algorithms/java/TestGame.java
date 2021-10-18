package com.sky.hiwise.algorithms.java;

import java.util.ArrayList;
import java.util.List;

public class TestGame {
    /**
     * 两个乒乓球队进行比赛，各出三人。甲队为a,b,c三人，乙队为x,y,z三人。已抽签决定比赛名单。有人向队员打听比赛的名单。a说他不和x比，c说他不和x,z比，请编程序找出三队赛手的名单
     */
    public static void main(String[] args) {
        char a = ' ', b = ' ', c = ' ';
        String s = "xyz";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'x' && s.charAt(i) != 'z') {
                c = s.charAt(i);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'x' && c != s.charAt(i)) {
                a = s.charAt(i);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != a && c != s.charAt(i)) {
                b = s.charAt(i);
            }
        }
        System.out.println("a->" + a);
        System.out.println("b->" + b);
        System.out.println("c->" + c);
    }

}
