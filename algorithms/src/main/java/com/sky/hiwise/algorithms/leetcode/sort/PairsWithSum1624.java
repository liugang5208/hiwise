package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairsWithSum1624 {
    /**
     * 面试题 16.24. 数对和
     * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
     *
     * 示例 1:
     *
     * 输入: nums = [5,6,5], target = 11
     * 输出: [[5,6]]
     * 示例 2:
     *
     * 输入: nums = [5,6,5,6], target = 11
     * 输出: [[5,6],[5,6]]
     */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 0) {
            return res;
        }

        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left] + nums[right] ;
            if (temp == target) {
                res.add(Arrays.asList(nums[left], nums[right]));
                left++;
                right--;
            } else if (temp < target) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}
