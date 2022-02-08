package com.sky.hiwise.algorithms.leetcode.slidwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagrams438 {
    /**
     * 438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * 示例 1:
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *  示例 2:
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] sCnt = new int[26];
        int[] pCnt = new int[26];
        int sLen = s.length(), pLen = p.length();
        List<Integer> ans = new ArrayList<>();
        if (sLen < pLen) {
            return ans;
        }
        for(int i = 0; i < p.length(); i++) {
            sCnt[s.charAt(i) - 'a'] ++;
            pCnt[p.charAt(i) - 'a'] ++;
        }
        if (Arrays.equals(sCnt, pCnt)) {
            ans.add(0);
        }
        for (int i = 0; i < sLen - pLen; i++) {
            sCnt[s.charAt(i) - 'a']--;
            sCnt[s.charAt(i + pLen) - 'a']++;  //累加计算pLen之后的数量
            if (Arrays.equals(sCnt, pCnt)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

}
