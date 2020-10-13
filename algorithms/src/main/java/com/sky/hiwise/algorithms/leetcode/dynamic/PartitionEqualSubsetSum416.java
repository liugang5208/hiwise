package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class PartitionEqualSubsetSum416 {

    /**
     * 416. 分割等和子集
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * 注意:
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * 示例 1:
     * 输入: [1, 5, 11, 5]
     * 输出: true
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     * 示例 2:
     * 输入: [1, 2, 3, 5]
     * 输出: false
     * 解释: 数组不能分割成两个元素和相等的子集.
     * @param nums
     * @return
     */
    //[1...i] i个物品中将容量为C的背包装满
    //F(i,C) = F(i-1,C) || F(i-1, C-W[i])
    //memo[i][c] 表示使用索引为[0..i]的这些元素是否可以完全填充一个容量为c的背包
    int memo[][];
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }
        int total = 0;
        for(int i = 0; i < len; i++) {
            total += nums[i];
        }
        if (total == 0 || total % 2 != 0) {
            return false;
        }
        memo = new int[len][total/2 + 1];
        for (int i = 0; i < len; i++) {
            Arrays.fill(memo[i], -1);
        }

        return tryPartition(nums, len - 1, total / 2);
    }

    //使用nums [0..index]是否可以 完全填充一个容量为capacity的背包
    public boolean tryPartition(int[] nums, int index, int capacity) {
        if (index < 0 || capacity < 0) {
            return false;
        }
        if (capacity == 0) {
            return true;
        }
        if (memo[index][capacity] != -1) {
            return memo[index][capacity] == 1;
        }
        memo[index][capacity] = (tryPartition(nums, index - 1, capacity)
                || tryPartition(nums, index - 1, capacity - nums[index])) ? 1 :0;
        return memo[index][capacity] == 1;
    }

    boolean[] dp;
    public boolean canPartitionDp(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }
        int total = 0;
        for(int i = 0; i < len; i++) {
            total += nums[i];
        }
        if (total == 0 || total % 2 != 0) {
            return false;
        }
        int capacity = total / 2;
        dp = new boolean[capacity + 1];
        for(int k = 0; k <= capacity; k++) {
            dp[k] = (nums[0] == k);
        }
        for(int i = 1; i < len; i++) {
            for(int j = capacity; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[capacity];
    }
}
