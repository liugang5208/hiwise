package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses301 {
    /**
     * 301. 删除无效的括号
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     * 示例 1：
     * 输入：s = "()())()"
     * 输出：["(())()","()()()"]
     * 示例 2：
     * 输入：s = "(a)())()"
     * 输出：["(a())()","(a)()()"]
     * 示例 3：
     * 输入：s = ")("
     * 输出：[""]
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> currSet = new HashSet<>();
        currSet.add(s);

        while (true) {
            for (String curr : currSet) {
                if (isValid(curr)) {
                    ans.add(curr);
                }
            }
            if (ans.size() > 0) {
                return ans;
            }
            Set<String> nextSet = new HashSet<>();
            for (String curr : currSet) {
                for (int i = 0; i < curr.length(); i++) {
                    if (i > 0 && curr.charAt(i) == curr.charAt(i - 1)) {
                        continue;
                    }
                    if (curr.charAt(i) == '(' || curr.charAt(i) == ')') {
                        nextSet.add(curr.substring(0, i) + curr.substring(i + 1));
                    }
                }
            }
            currSet = nextSet;
        }
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }
}
