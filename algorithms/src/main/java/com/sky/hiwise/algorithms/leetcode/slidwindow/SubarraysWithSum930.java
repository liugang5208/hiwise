package com.sky.hiwise.algorithms.leetcode.slidwindow;

public class SubarraysWithSum930 {
    /**
     * 930. 和相同的二元子数组
     * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
     *
     * 子数组 是数组的一段连续部分。
     * 示例 1：
     *
     * 输入：nums = [1,0,1,0,1], goal = 2
     * 输出：4
     * 解释：
     * 如下面黑体所示，有 4 个满足题目要求的子数组：
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     * [1,0,1,0,1]
     */
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length, right = 0, left1 = 0, left2 = 0, sum1 = 0, sum2 = 0, ans = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            ans += left2 - left1;
            right++;
        }
        return ans;
    }
}
