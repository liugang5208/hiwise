package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class MaxLengthSubArray718 {
    /**
     * 718. 最长重复子数组
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     * 示例 1:
     * 输入:
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * 输出: 3
     * 解释:
     * 长度最长的公共子数组是 [3, 2, 1]。
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int ans = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        return ans;
    }

    public int findLengthDp(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int ans = 0;
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], 0);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
