package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2022-02-09 10:41
 **/
public class CountNumPairsDiffK2006 {

    /**
     * 2006. 差的绝对值为 K 的数对数目
     * 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
     * |x| 的值定义为：
     * 如果 x >= 0 ，那么值为 x 。
     * 如果 x < 0 ，那么值为 -x 。
     * 示例 1：
     * 输入：nums = [1,2,2,1], k = 1
     * 输出：4
     * 解释：差的绝对值为 1 的数对为：
     * - [1,2,2,1]
     * - [1,2,2,1]
     * - [1,2,2,1]
     * - [1,2,2,1]
     */
    public int countKDifference(int[] nums, int k) {
        int ans = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ans ++;
                }
            }
        }
        return ans;
    }

    public int countKDifference1(int[] nums, int k) {
        int ans = 0, len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            ans += map.getOrDefault(nums[i] - k, 0) + map.getOrDefault(nums[i] + k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return ans;
    }
}
