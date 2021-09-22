package com.sky.hiwise.algorithms.leetcode.stack;

import java.util.Stack;

public class ValidParenthesisString678 {

    /**
     * 678. 有效的括号字符串
     * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
     * 任何左括号 ( 必须有相应的右括号 )。
     * 任何右括号 ) 必须有相应的左括号 ( 。
     * 左括号 ( 必须在对应的右括号之前 )。
     * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
     * 一个空字符串也被视为有效字符串。
     * 示例 1:
     * 输入: "()"
     * 输出: True
     * 示例 2:
     * 输入: "(*)"
     * 输出: True
     * 示例 3:
     * 输入: "(*))"
     * 输出: True
     */
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] == '(') {
                stack.add(i);
            } else if (ss[i] == '*') {
                starStack.add(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    //2个栈都为空，）左括号没有匹配的返回false
                    return false;
                }
            }
        }
        while (!stack.isEmpty() && !starStack.isEmpty()) {
            int sIdx = stack.pop(), ssIdx = starStack.pop();
            if (sIdx > ssIdx) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
