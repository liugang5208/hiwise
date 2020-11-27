package com.sky.hiwise.algorithms.leetcode.array;

public class MaxProductInArray1464 {
    /**
     * 1464. 数组中两元素的最大乘积
     * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
     * 请你计算并返回该式的最大值。
     * 示例 1：
     * 输入：nums = [3,4,5,2]
     * 输出：12
     * 解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
     */
    public int maxProduct(int[] nums) {
        int a = nums[0];
        int b = nums[1];
        for (int i = 2; i < nums.length; i++) {
            if (a > b) {
                b = Math.max(nums[i], b);
            } else {
                a = Math.max(nums[i], a);
            }
        }
        return (a - 1) * (b - 1);
    }
    /**
     * 解题思路
     * 取前两个元素作为初始的两个值
     * 从下标 2 开始遍历数组
     * a > b 的时候更新 b 值
     * a <= b 的时候更新 a 值
     */
}
