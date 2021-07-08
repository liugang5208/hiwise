package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.*;

public class Lcof38 {

    /**
     * 剑指 Offer 38. 字符串的排列
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     * 示例:
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     */
    List<String> res = new ArrayList<>();
    boolean[] used;
    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[]{};
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);  //先排序
        used = new boolean[chars.length];
        backtrace(chars, 0,"");
        return res.toArray(new String[res.size()]);
    }

    public void backtrace(char[] chars, int idx, String curr) {
        if (idx == chars.length) {
            res.add(curr);
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            //过滤重复
            if (used[i] || (i > 0 && !used[i - 1] && chars[i - 1] == chars[i])) {
                continue;
            }
            used[i] = true;
            backtrace(chars, idx + 1, curr + chars[i]);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        //输入：s = "abc"
        //输出：["abc","acb","bac","bca","cab","cba"]
        String s = "abc";

        String[] test = (new Lcof38()).permutation(s);

        for (String t : test) {
            System.out.println(t);
        }
    }
}
