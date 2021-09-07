package com.sky.hiwise.algorithms.leetcode.math;

public class SumAllOddSubArrays1588 {

    /**
     * 1588. 所有奇数长度子数组的和
     * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
     * 子数组 定义为原数组中的一个连续子序列。
     * 请你返回 arr 中 所有奇数长度子数组的和 。
     * 示例 1：
     * 输入：arr = [1,4,2,5,3]
     * 输出：58
     * 解释：所有奇数长度子数组和它们的和为：
     * [1] = 1
     * [4] = 4
     * [2] = 2
     * [5] = 5
     * [3] = 3
     * [1,4,2] = 7
     * [4,2,5] = 11
     * [2,5,3] = 10
     * [1,4,2,5,3] = 15
     * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        //先求前缀和
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }
        int sum = 0;
        for (int start = 0; start < n; start ++) {
            for (int len = 1; start + len <= n; len += 2) {
                sum += prefixSum[start + len] - prefixSum[start];
            }
        }
        return sum;
    }
}
