package com.sky.hiwise.algorithms.leetcode.string;

import java.util.List;

public class LongestWordInDictionary524 {
    /**
     * 524. 通过删除字母匹配到字典里最长单词
     * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
     * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
     * 示例 1：
     * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
     * 输出："apple"
     * 示例 2：
     * 输入：s = "abpcplea", dictionary = ["a","b","c"]
     * 输出："a"
     */
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String d : dictionary) {
            int i = 0, j = 0;
            while (i < d.length() && j < s.length()) {
                if (d.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == d.length()) {
                if (ans.length() < d.length() || (ans.length() == d.length() && d.compareTo(ans) < 0)) {
                    ans = d;
                }
            }
        }
        return ans;
    }
}
