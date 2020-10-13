package com.sky.hiwise.algorithms.leetcode.string;

public class FindWordsInChars1160 {
    /**
     * 1160. 拼写单词
     * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
     *
     * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
     *
     * 注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。
     *
     * 返回词汇表 words 中你掌握的所有单词的 长度之和。
     *
     *
     *
     * 示例 1：
     *
     * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
     * 输出：6
     * 解释：
     * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
     */
    public int countCharacters(String[] words, String chars) {
        if (chars == null || chars.length() == 0) {
            return 0;
        }
        int[] charWords = new int[26];
        for (char c : chars.toCharArray()) {
            charWords[c - 'a'] ++;
        }
        int ans = 0;
        for (String word : words) {
            int[] temp = new int[26];
            for (int i = 0; i < word.length(); i++) {
                temp[word.charAt(i) - 'a'] ++;
            }
            boolean isContains = true;
            for (int j = 0; j < 26; j++) {
                if (temp[j] > charWords[j]) {
                    isContains = false;
                    break;
                }
            }
            if (isContains) {
                ans += word.length();
            }
        }
        return ans;
    }
}
