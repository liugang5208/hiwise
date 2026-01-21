package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence128 {

    /**
     * 128. 最长连续序列
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     * 要求算法的时间复杂度为 O(n)。
     * 示例:
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                int preLen = map.getOrDefault(num - 1, 0);
                int postLen = map.getOrDefault(num + 1, 0);
                int all = preLen + postLen + 1;
                map.put(num - preLen, all);
                map.put(num + postLen, all);
                ans = Math.max(ans, all);
            }
        }
        return ans;
    }

    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> sets = new HashSet<>();
        for (int num : nums) {
            sets.add(num);
        }
        int ans = 0;
        for (int num : sets) {
            if (!sets.contains(num - 1)) {
                int curLen = 1;
                int curNum = num;
                while (sets.contains(curNum + 1)) {
                    curLen++;
                    curNum++;
                }
                ans = Math.max(ans, curLen);
            }
        }
        return ans;
    }

}
