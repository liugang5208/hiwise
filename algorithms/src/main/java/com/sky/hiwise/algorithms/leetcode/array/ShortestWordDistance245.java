package com.sky.hiwise.algorithms.leetcode.array;

public class ShortestWordDistance245 {
    /**
     * [LeetCode] 245. Shortest Word Distance III 最短单词距离之三
     * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
     *
     * word1 and word2 may be the same and they represent two individual words in the list.
     *
     * Example:
     * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
     *
     * Input: word1 = “makes”, word2 = “coding”
     * Output: 1
     * Input: word1 = "makes", word2 = "makes"
     * Output: 3
     * Note:
     * You may assume word1 and word2 are both in the list.
     */

    public int shortest(String[] words, String start, String end) {
        int pos1 = -1, pos2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            int t = pos1;
            if (words[i].equals(start)) {
                pos1 = i;
            } else if (words[i].equals(end)) {
                pos2 = i;
            }
            if (pos1 != -1 && pos2 != -1) {
                if (start.equals(end) && t != -1 && t != pos1) {
                    res = Math.min(res, Math.abs(t - pos1));
                } else if (pos1 != pos2) {
                    res = Math.min(res, Math.abs(pos2 - pos1));
                }
            }
        }
        return res;
    }

    /**
     * 由于会有相同的单词的情况，那么 p1 和 p2 就会相同，这样结果就会变成0，显然不对，
     * 所以要对 word1 和 word2 是否的相等的情况分开处理，
     * 如果相等了，由于 p1 和 p2 会相同，所以需要一个变量t来记录上一个位置，这样如果t不为 -1，
     * 且和当前的 p1 不同，可以更新结果，
     * 如果 word1 和 word2 不等，那么还是按原来的方法做
     */

    /**
     * 我们并不需要变量t来记录上一个位置，
     * 将 p1 初始化为数组长度，p2 初始化为数组长度的相反数，
     * 然后当 word1 和 word2 相等的情况，用 p1 来保存 p2 的结果，p2 赋为当前的位置i，
     * 这样就可以更新结果了，如果 word1 和 word2 不相等，
     * 则还跟原来的做法一样，这种思路真是挺巧妙的
     * @param words
     * @param start
     * @param end
     * @return
     */
    public int shortest1(String[] words, String start, String end) {
        int pos1 = words.length, pos2 = - words.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(start)) {
                pos1 = start.equals(end) ? pos2 : i;
            } else if (words[i].equals(end)) {
                pos2 = i;
            }
            res = Math.min(res, Math.abs(pos2 - pos1));
        }
        return res;
    }

    /**
     * 我们再来看一种更进一步优化的方法，
     * 只用一个变量 idx，这个 idx 的作用就相当于记录上一次的位置，
     * 当前 idx 不等 -1 时，说明当前i和 idx 不同，
     * 然后在 word1 和 word2 相同或者 words[i] 和 words[idx] 相同的情况下更新结果，
     * 最后别忘了将 idx 赋为i，参见代码如下；
     */
    public int shortest2(String[] words, String start, String end) {
        int idx = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(start) || words[i].equals(end)) {
                if (idx != -1 && (start.equals(end) || words[i] != words[idx])) {
                    res = Math.min(res, Math.abs(i - idx));
                }
                idx = i;
            }
        }
        return res;
    }
}
