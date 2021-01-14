package com.sky.hiwise.algorithms.leetcode.array;

import java.util.Deque;
import java.util.LinkedList;

public class ShortestSubArrayWithSumK862 {
    /**
     * 862. 和至少为 K 的最短子数组
     * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
     * 如果没有和至少为 K 的非空子数组，返回 -1 。
     * 示例 1：
     * 输入：A = [1], K = 1
     * 输出：1
     * 示例 2：
     * 输入：A = [1,2], K = 4
     * 输出：-1
     * 示例 3：
     * 输入：A = [2,-1,2], K = 3
     * 输出：3
     * 区别于第209题， 正整数数组 和 有负数的数组
     */
    public int shortestSubarray(int[] A, int K) {

        if (A == null ||  A.length == 0) {
            return -1;
        }
        int n = A.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + A[i];
        }
        int ans = n + 1;

        Deque<Integer> deque = new LinkedList<>();

        for (int y = 0; y < sums.length; y++) {
            while (!deque.isEmpty() && sums[y] <= sums[deque.getLast()]) {
                deque.removeLast();
            }
            while (!deque.isEmpty() && sums[y] >= sums[deque.getFirst()] + K) {
                ans = Math.min(ans, deque.removeFirst());
            }
            deque.addLast(y);
        }

        return ans < n + 1 ? ans : -1;
    }

    public static void main(String[] args) {
        //[84,-37,32,40,95]
        //167
        int[] A = new int[]{84,-37,32,40,95};
        int K = 167;
        System.out.println((new ShortestSubArrayWithSumK862()).shortestSubarray(A, K));
    }
}
