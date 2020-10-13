package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.List;

public class Triangle120 {

    /**
     * 120. 三角形最小路径和
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     * 例如，给定三角形：
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if (len == 1) {
            return triangle.get(0).get(0);
        }

        int[][] dp = new int[len + 1][len + 1];
        dp[0][0] = triangle.get(0).get(0);
        dp[1][0] = dp[0][0] + triangle.get(1).get(0);
        dp[1][1] = dp[0][0] + triangle.get(1).get(1);
        for (int i = 2; i < triangle.size(); i++) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == triangle.get(i).size() - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + triangle.get(i).get(j), dp[i - 1][j - 1] + triangle.get(i).get(j));
                }
            }
        }
        int min =  Integer.MAX_VALUE;
        for(int i = 0; i < triangle.get(len - 1).size(); i++) {
            min = Math.min(min, dp[len - 1][i]);
        }
        return min;
    }
    /**
     * 思路1：自顶向下，递推公式 minPath[i][j] = Min(minPath[i-1][j-1], minPath[i-1][j]) + a[i][j],
     * dp[i][j] = Min(dp[i - 1][j - 1], dp[i - 1][j]) + 1
     * 但显然要考虑特殊边界位置，即最左侧，最右侧位置是不同的。
     */
}
