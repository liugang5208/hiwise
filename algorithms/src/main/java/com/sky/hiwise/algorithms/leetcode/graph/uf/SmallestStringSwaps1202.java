package com.sky.hiwise.algorithms.leetcode.graph.uf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SmallestStringSwaps1202 {
    /**
     * 1202. 交换字符串中的元素
     * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
     * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
     * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
     * 示例 1:
     * 输入：s = "dcab", pairs = [[0,3],[1,2]]
     * 输出："bacd"
     * 解释：
     * 交换 s[0] 和 s[3], s = "bcad"
     * 交换 s[1] 和 s[2], s = "bacd"
     * 示例 2：
     * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
     * 输出："abcd"
     * 解释：
     * 交换 s[0] 和 s[3], s = "bcad"
     * 交换 s[0] 和 s[2], s = "acbd"
     * 交换 s[1] 和 s[2], s = "abcd"
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs == null || pairs.size() == 0) {
            return s;
        }
        int len = s.length();
        UnionFind uf = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            int root = uf.find(i);
            map.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(chars[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = uf.find(i);
            sb.append(map.get(root).poll());
        }
        return sb.toString();
    }
}
