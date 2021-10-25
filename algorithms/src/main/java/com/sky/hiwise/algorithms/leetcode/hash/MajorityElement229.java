package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElement229 {
    /**
     * 229. 求众数 II
     * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     * 示例 1：
     * 输入：[3,2,3]
     * 输出：[3]
     * 示例 2：
     * 输入：nums = [1]
     * 输出：[1]
     * 示例 3：
     * 输入：[1,1,1,3,3,2,2,2]
     * 输出：[1,2]
     */
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int n = nums.length / 3;
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }
}
