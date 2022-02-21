package com.sky.hiwise.algorithms.leetcode.slidwindow;

import java.util.Arrays;

/**
 * @date: 2022-02-11 15:56
 **/
public class MinDiffKScores1984 {

    /**
     * 1984. 学生分数的最小差值
     * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
     * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
     * 返回可能的 最小差值 。
     * 示例 1：
     * 输入：nums = [90], k = 1
     * 输出：0
     * 解释：选出 1 名学生的分数，仅有 1 种方法：
     * - [90] 最高分和最低分之间的差值是 90 - 90 = 0
     * 可能的最小差值是 0
     */
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < nums.length; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
