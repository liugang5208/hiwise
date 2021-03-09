package com.sky.hiwise.algorithms.leetcode.sort;

public class MonotonicArray896 {
    /**
     * 896. 单调数列
     * 如果数组是单调递增或单调递减的，那么它是单调的。
     * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
     * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
     * 示例 1：
     * 输入：[1,2,2,3]
     * 输出：true
     * 示例 2：
     * 输入：[6,5,4,4]
     * 输出：true
     * 示例 3：
     * 输入：[1,3,2]
     * 输出：false
     */
    public boolean isMonotonic(int[] A) {
        boolean inc = true, dec = true;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] < A[i + 1]) {
                dec = false;
            }
            if (A[i] > A[i + 1]) {
                inc = false;
            }
        }
        return inc || dec;
    }
}
