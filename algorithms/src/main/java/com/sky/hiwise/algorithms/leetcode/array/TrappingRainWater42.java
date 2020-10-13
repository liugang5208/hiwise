package com.sky.hiwise.algorithms.leetcode.array;

public class TrappingRainWater42 {

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）
     * 示例:
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     * https://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/jie-yu-shui
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        rightMax[n - 1] = height[n - 1];
        int ans = 0;
        for(int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        for (int j = n - 2; j > 0; j--) {
            rightMax[j] = Math.max(height[j], rightMax[j + 1]);
        }
        for (int i = 1; i < n; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if (height[i] < min) {
                ans += min - height[i];
            }
        }
        return ans;
    }

    public int trap1(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int leftMax = height[0], rightMax = height[n - 1];
        int left = 0, right = n - 1;
        int ans = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // ans += min(l_max, r_max) - height[i]
            if (leftMax < rightMax) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
}
