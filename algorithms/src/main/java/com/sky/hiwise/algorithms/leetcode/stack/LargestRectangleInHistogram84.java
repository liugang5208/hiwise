package com.sky.hiwise.algorithms.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram84 {
    /**
     * 84. 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
     * 示例:
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     * 通过次数116,069提交次数272,700
     */
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        int n = heights.length;
        for (int i = 0; i < n; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int right = i; right < n; right++) {
                minHeight = Math.min(minHeight, heights[right]);
                ans = Math.max(ans, (right - i + 1) * minHeight);
            }
        }
        return ans;
    }

    public int largestRectangleArea1(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{6, 7, 5, 2, 4, 5, 9, 3};
        System.out.println((new LargestRectangleInHistogram84()).largestRectangleArea1(heights));
    }


}
