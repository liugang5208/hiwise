package com.sky.hiwise.algorithms.leetcode.string;

public class LastWordLength58 {
    /**
     * 58. 最后一个单词的长度
     * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     * 示例 1：
     * 输入：s = "Hello World"
     * 输出：5
     * 示例 2：
     * 输入：s = "   fly me   to   the moon  "
     * 输出：4
     * 示例 3：
     * 输入：s = "luffy is still joyboy"
     * 输出：6
     */
    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int wordLen = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLen ++;
            index--;
        }
        return wordLen;
    }
}
