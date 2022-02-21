package com.sky.hiwise.algorithms.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

public class SumOfUniqueElements1748 {

    /**
     * 1748. 唯一元素的和
     * 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
     * 请你返回 nums 中唯一元素的 和 。
     * 示例 1：
     * 输入：nums = [1,2,3,2]
     * 输出：4
     * 解释：唯一元素为 [1,3] ，和为 4 。
     * 示例 2：
     * 输入：nums = [1,1,1,1,1]
     * 输出：0
     * 解释：没有唯一元素，和为 0 。
     * @param nums
     * @return
     */
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                ans += num;
                map.put(num, 1);
            } else if (map.get(num) == 1) {
                ans -= num;
                map.put(num, 2);
            }
        }
        return ans;
    }
}
