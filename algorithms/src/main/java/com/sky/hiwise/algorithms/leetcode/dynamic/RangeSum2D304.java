package com.sky.hiwise.algorithms.leetcode.dynamic;

public class RangeSum2D304 {

    /**
     * 304. 二维区域和检索 - 矩阵不可变
     * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
     *
     * Range Sum Query 2D
     * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
     *
     * 示例:
     */
    private int[][] dp;
    public RangeSum2D304(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m][n + 1];
        for(int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                dp[r][c + 1] = matrix[r][c] + dp[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            sum += dp[row][col2 + 1] - dp[row][col1];
        }
        return sum;
    }
}
