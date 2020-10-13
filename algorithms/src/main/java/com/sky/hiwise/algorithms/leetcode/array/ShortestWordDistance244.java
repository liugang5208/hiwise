package com.sky.hiwise.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShortestWordDistance244 {

    /**
     * [LeetCode] Shortest Word Distance II 最短单词距离之二
     * This is a follow up of Shortest Word Distance.
     * The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters.
     * How would you optimize it?
     * Design a class which receives a list of words in the constructor,
     * and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
     * For example,
     * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
     *
     * Given word1 = “coding”, word2 = “practice”, return 3.
     * Given word1 = "makes", word2 = "coding", return 1.
     *
     * Note:
     * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
     */
    private HashMap<String, List<Integer>> map;
    public ShortestWordDistance244(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.getOrDefault(words[i], new ArrayList<>()).add(i);
        }

    }
    public int shortest(String start, String end) {
        int i = 0, j = 0, res = Integer.MAX_VALUE;
        List<Integer> startList = map.get(start);
        List<Integer> endList = map.get(end);
        while(i < startList.size() && j < endList.size()) {
            res = Math.min(res, Math.abs(startList.get(i) - endList.get(j)));
            if (startList.get(i) < endList.get(j)) {
                i++;
            } else {
                j++;
            }
        }

        return res;
    }
}
