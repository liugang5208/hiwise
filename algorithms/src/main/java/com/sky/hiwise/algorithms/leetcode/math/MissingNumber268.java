package com.sky.hiwise.algorithms.leetcode.math;

public class MissingNumber268 {

    /**
     * 268. 缺失数字
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     * 示例 1:
     * 输入: [3,0,1]
     * 输出: 2
     * 示例 2:
     * 输入: [9,6,4,2,3,5,7,0,1]
     * 输出: 8
     * 说明:
     * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        res ^= n;
        for (int i = 0; i < n; i ++) {
            res ^= i ^ nums[i];
        }
        return res;
    }

    public int missingNumber1(int[] nums) {
        int n = nums.length;
        int expect = (0 + n) * (n + 1) / 2;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        return expect - sum;
    }
}
