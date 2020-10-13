package com.sky.hiwise.algorithms.leetcode.array;

public class ShortestWordDistance243 {

    /**
     * 243 最短单词距离
     * Given a list of words and two words word1 and word2,
     * return the shortest distance between these two words in the list.
     * For example,
     * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
     * Given word1 = “coding”, word2 = “practice”, return 3.
     * Given word1 = "makes", word2 = "coding", return 1.
     * Note:
     * You may assume that word1 does not equal to word2,
     * and word1 and word2 are both in the list.
     * @param words
     * @param start
     * @param end
     * @return
     */
    public int shortest(String[] words, String start, String end) {
        int pos1 = -1, pos2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(start)) {
                pos1 = i;
            } else if (words[i].equals(end)) {
                pos2 = i;
            }
            if (pos1 != -1 && pos2 != -1) {
                res = Math.min(res, Math.abs(pos2 - pos1));
            }
        }
        return res;
    }
    /**
     * 上面的那种方法并不高效，我们其实需要遍历一次数组就可以了，
     * 我们用两个变量p1,p2初始化为-1，然后我们遍历数组，
     * 遇到单词1，就将其位置存在p1里，若遇到单词2，就将其位置存在p2里，
     * 如果此时p1, p2都不为-1了，那么我们更新结果
     */

    /**
     * 下面这种方法只用一个辅助变量idx，初始化为-1，然后遍历数组，
     * 如果遇到等于两个单词中的任意一个的单词，
     * 我们在看idx是否为-1，若不为-1，且指向的单词和当前遍历到的单词不同，我们更新结果，参见代码如下：
     */
    public int shortest1(String[] words, String start, String end) {
        int idx = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(start) || words[i].equals(end)) {
                if (idx != -1 && words[idx] != words[i]) {
                    res = Math.min(res, Math.abs(i - idx));
                }
                idx = i;
            }
        }
        return res;
    }
}
