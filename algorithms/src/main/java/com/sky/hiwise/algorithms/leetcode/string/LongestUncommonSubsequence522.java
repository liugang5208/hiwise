package com.sky.hiwise.algorithms.leetcode.string;

import org.springframework.beans.factory.BeanFactory;

public class LongestUncommonSubsequence522 {

    /**
     * 522. 最长特殊序列 II
     * 给定字符串列表，你需要从它们中找出最长的特殊序列。
     * 最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     * 子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。
     * 空序列为所有字符串的子序列，任何字符串为其自身的子序列。
     * 输入将是一个字符串列表，输出是最长特殊序列的长度。如果最长特殊序列不存在，返回 -1 。
     * 示例：
     * 输入: "aba", "cdc", "eae"
     * 输出: 3
     * 提示：
     * 所有给定的字符串长度不会超过 10 。
     * 给定字符串列表的长度将在 [2, 50 ] 之间。
     * @param strs
     * @return
     */
    public static int findLUSlength(String[] strs) {
        int ans = -1;
        for(int i = 0, j; i < strs.length; i++) {
            for (j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isSequence(strs[i], strs[j])) {
                    break;
                }
            }
            if (j == strs.length) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    public static boolean isSequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++)
            if (x.charAt(j) == y.charAt(i))
                j++;
        return j == x.length();

//        int i = 0, j = 0;
//        while(i < s.length() && j < t.length()) {
//            if (s.charAt(i) != t.charAt(j)) {
//                j++;
//            }
//            i++;
//        }
//        return j == t.length();
    }

    public static void main(String[] args) {
        //BeanFactory
        //["aba","cdc","eae"]
        System.out.println(isSequence("aaa", "aa"));
        String[] test = {"aaa", "aa", "a"};
        System.out.println(findLUSlength(test));
    }
}
