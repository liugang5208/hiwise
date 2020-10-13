package com.sky.hiwise.algorithms.algo;

import java.util.Stack;

public class Test {

    public static void main(String[] args) {
        String s = "{{}}{}";
        System.out.println(isMatch(s));
    }

    public static boolean isMatch(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Stack<String> stack = new Stack<>();
        int len = s.length();
        for (int i = 0 ; i< len; i++){
            if(s.charAt(i) == '{') {
                stack.push(String.valueOf(s.charAt(i)));
            } else if (s.charAt(i) == '}'){
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
