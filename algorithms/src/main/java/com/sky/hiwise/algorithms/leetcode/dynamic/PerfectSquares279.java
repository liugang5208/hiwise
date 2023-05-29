package com.sky.hiwise.algorithms.leetcode.dynamic;

public class PerfectSquares279 {

    /**
     * 279. 完全平方数
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * 示例 1:
     * 输入: n = 12
     * 输出: 3
     * 解释: 12 = 4 + 4 + 4.
     * 示例 2:
     * 输入: n = 13
     * 输出: 2
     * 解释: 13 = 4 + 9.
     * @param n
     * @return
     */
    int memo[];
    public int numSquares(int n) {
        memo = new int[n + 1];
        return numSqu(n);
    }

    public int numSqu(int n) {
        if (memo[n] != 0) {
            return memo[n];
        }
        int val = (int) Math.sqrt(n);
        if (val * val == n) {
            return memo[n] = 1;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i * i < n; i++) {
            res = Math.min(res, numSqu(n - i * i) + 1);
        }

        memo[n] = res;
        return res;
    }

    /**
     * 标签：动态规划
     * 首先初始化长度为n+1的数组dp，每个位置都为0
     * 如果n为0，则结果为0
     * 对数组进行遍历，下标为i，每次都将当前数字先更新为最大的结果，即dp[i]=i，比如i=4，最坏结果为4=1+1+1+1即为4个数字
     * 动态转移方程为：dp[i] = MIN(dp[i], dp[i - j * j] + 1)，i表示当前数字，j*j表示平方数
     * 时间复杂度：O(n*sqrt(n))，sqrt为平方根
     */
    public int numSquaresDp(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        for(int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for(int j = 1; j * j <= i; j++) {
                // 动态转移方程
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
