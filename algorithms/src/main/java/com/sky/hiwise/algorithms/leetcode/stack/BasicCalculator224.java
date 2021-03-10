package com.sky.hiwise.algorithms.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BasicCalculator224 {
    /**
     * 224. 基本计算器
     * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
     * 示例 1：
     * 输入：s = "1 + 1"
     * 输出：2
     * 示例 2：
     * 输入：s = " 2-1 + 2 "
     * 输出：3
     * 示例 3：
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     */
    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<>();
        ops.add(1);
        int ans = 0, n = s.length(), sign = 1, i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ans += sign * num;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "- (3 + (4 + 5))";
        System.out.println((new BasicCalculator224()).calculate(s));
    }
}
