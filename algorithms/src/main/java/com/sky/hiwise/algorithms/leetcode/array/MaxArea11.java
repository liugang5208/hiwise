package com.sky.hiwise.algorithms.leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

public class MaxArea11 {

    public static void main(String[] args) {
        Queue<int[][]> queue = new LinkedList<>();

    }

    /**
     * 11. 盛最多水的容器
     给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     画 n 条垂直线，使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     注意：你不能倾斜容器，n 至少是2。
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int start = 0;
        int end = height.length - 1;
        int maxArea = 0;
        int currentArea = 0;
        while (start < end) {
            if (height[start] < height[end]) {
                currentArea = height[start] * (end - start);
                start ++;
            } else {
                currentArea = height[end] * (end - start);
                end --;
            }
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }
        }

        return maxArea;
    }

}
