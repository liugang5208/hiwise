package com.sky.hiwise.algorithms.leetcode.interview;

public class FindClosest1711 {

    /**
     * 面试题 17.11. 单词距离
     * 有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
     * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
     * 示例：
     * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
     * 输出：1
     * 提示：
     * words.length <= 100000
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int findClosest(String[] words, String word1, String word2) {
        int len = words.length;
        int idx1 = -1, idx2 = -1;
        int ans = len;
        for (int i = 0; i < len; i++) {
            if (words[i].equals(word1)) {
                idx1 = i;
            } else if (words[i].equals(word2)) {
                idx2 = i;
            }
            ans = (idx1 > 0 && idx2 > 0) ? Math.min(Math.abs(idx2 - idx1), ans) : ans;
        }
        return ans;
    }
    //双指针
}
