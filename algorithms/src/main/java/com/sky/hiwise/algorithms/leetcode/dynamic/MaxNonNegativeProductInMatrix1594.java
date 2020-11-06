package com.sky.hiwise.algorithms.leetcode.dynamic;

public class MaxNonNegativeProductInMatrix1594 {

    /**
     * 1594. 矩阵的最大非负积
     * 给你一个大小为 rows x cols 的矩阵 grid 。最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
     * 在从左上角 (0, 0) 开始到右下角 (rows - 1, cols - 1) 结束的所有路径中，找出具有 最大非负积 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。
     * 返回 最大非负积 对 109 + 7 取余 的结果。如果最大积为负数，则返回 -1 。
     * 注意，取余是在得到最大积之后执行的。
     * 示例 1：
     *
     * 输入：grid = [[-1,-2,-3],
     *              [-2,-3,-3],
     *              [-3,-3,-2]]
     * 输出：-1
     * 解释：从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1
     */
    public int maxProductPath(int[][] grid) {
        final int mod = 1000000000 + 7;
        int m = grid.length, n = grid[0].length;
        long[][] max = new long[m][n];
        long[][] min = new long[m][n];
        max[0][0] = min[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            max[i][0] = min[i][0] = max[i - 1][0] * grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            max[0][j] = min[0][j] = max[0][j - 1] * grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] >= 0) {
                    max[i][j] = Math.max(max[i - 1][j], max[i][j - 1]) * grid[i][j];
                    min[i][j] = Math.min(min[i - 1][j], min[i][j - 1]) * grid[i][j];
                } else {
                    max[i][j] = Math.min(min[i - 1][j], min[i][j - 1]) * grid[i][j];
                    min[i][j] = Math.max(max[i - 1][j], max[i][j - 1]) * grid[i][j];
                }
            }
        }
        if (max[m - 1][n - 1] < 0) {
            return -1;
        }
        return (int)(max[m - 1][n - 1] % mod);
    }
}
