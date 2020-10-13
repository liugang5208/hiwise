package com.sky.hiwise.algorithms.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePairs336 {

    /**
     * 336. 回文对
     * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
     * 示例 1:
     * 输入: ["abcd","dcba","lls","s","sssll"]
     * 输出: [[0,1],[1,0],[3,2],[2,4]]
     * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
     * 示例 2:
     * 输入: ["bat","tab","cat"]
     * 输出: [[0,1],[1,0]]
     * 解释: 可拼接成的回文串为 ["battab","tabbat"]
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        int len = words.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    continue;
                }
                String concat = words[i] + words[j];
                String reverse = new StringBuilder(concat).reverse().toString();
                if (concat.equals(reverse)) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }
}
