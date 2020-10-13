package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ReversePairs493 {
    public static void main(String[] args) {
        int[] nums = {1,3,2,3,1};
        System.out.println(solve(nums));
    }

    /**
     * 493. 翻转对
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     * 你需要返回给定数组中的重要翻转对的数量。
     * 示例 1:
     * 输入: [1,3,2,3,1]
     * 输出: 2
     * 示例 2:
     * 输入: [2,4,3,5,1]
     * 输出: 3
     * 注意:
     * 给定数组的长度不会超过50000。
     * 输入数组中的所有数字都在32位整数的表示范围内。
     */
    public static int solve(int[] nums) {
        return solve(nums, 1, nums.length - 1);
    }

    public static int solve(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = (right - left) / 2 + left;
        int leftCount = solve(nums, left, mid);
        int rightCount = solve(nums, mid + 1, right);
        int merge = merge(nums, left, mid, right);
        System.out.println("left: " + left  + " mid: " + mid + " right: " + right);
        System.out.println("leftCount: " + leftCount + " rightCount: " + rightCount + " merge: " + merge);
        return leftCount + rightCount + merge;
    }

    private static int merge(int[] nums, int left, int mid, int right) {
        int res = 0;
        int[] aux = Arrays.copyOfRange(nums, left, right + 1);
        int i = left, j = mid + 1;
        for(int k = left; k <= right; k++) {
            if (i > mid) {
                nums[k] = aux[j - left];
                j++;
            } else if (j > right) {
                nums[k] = aux[i - left];
                i++;
            } else if (aux[i - left] < aux[j - left]) {
                nums[k] = aux[i - left];
                i++;
            } else {
                nums[k] = aux[j - left];
                // 此时, 因为右半部分k所指的元素小
                // 这个元素和左半部分的所有未处理的元素都构成了逆序数对
                // 左半部分此时未处理的元素个数为 mid - i + 1
                if (aux[i - left] > 2 * aux[j - left]) {
                    res += (long)(mid - i + 1);
                }
                j++;
            }
        }
        return res;
    }

}
