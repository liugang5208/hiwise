package com.sky.hiwise.algorithms.leetcode.string;

public class LongestContinuouIncreasingSubsequence674 {

    /**
     * 674. 最长连续递增序列
     * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
     * 示例 1:
     * 输入: [1,3,5,4,7]
     * 输出: 3
     * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
     * 示例 2:
     *
     * 输入: [2,2,2,2,2]
     * 输出: 1
     * 解释: 最长连续递增序列是 [2], 长度为1。
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int ans = 1;
        int begin = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                ans = Math.max(ans, i - begin + 1);
            } else {
                begin = i;
            }
        }
        return ans;
    }
}
