package com.sky.hiwise.algorithms.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses20 {


    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<Character,Character>(){{
            put('{','}'); put('[',']'); put('(',')'); put('?','?');
        }};
        char[] ss = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (ss[i] == '(' || ss[i] == '{' || ss[i] == '[') {
                stack.add(ss[i]);
            } else {
                if (stack.isEmpty() || map.get(stack.pop()) != ss[i]) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
