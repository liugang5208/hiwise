package com.sky.hiwise.algorithms.leetcode.array;

public class MinSubArrayLen209 {

    /**
     * 滑动窗口
     209. 大于给定和最短子数组
     给定一个含有 n 个正整数的数组和一个正整数 s , 找到一个最小的连续子数组的长度，使得这个子数组的数字和 ≥  s 。如果不存在符合条件的子数组，返回 0。
     举个例子，给定数组 [2,3,1,2,4,3] 和 s = 7,
     子数组 [4,3]为符合问题要求的最小长度。
     更多联系:  如果你找到了O(n) 解法, 请尝试另一个时间复杂度为O(n log n)的解法。
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int start = 0, end = 0;  //nums[start...end]为我们的滑动窗口
        int sum = 0, minLength = Integer.MAX_VALUE;

        while (end < nums.length) {
            sum += nums[end];
            while (sum >= s) {
                minLength = minLength > end - start + 1 ? end - start + 1 : minLength;
                sum -= nums[start++];
            }
            end++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

}
