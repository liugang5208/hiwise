package com.sky.hiwise.algorithms.leetcode.math;

public class SurfaceArea3DShapes892 {

    /**
     * 892. 三维形体的表面积
     * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
     * 请你返回最终形体的表面积。
     * 示例 1：
     * 输入：[[2]]
     * 输出：10
     * @param grid
     * @return
     */
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                if (val > 0) {
                    // 一个柱体中：2个底面 + 所有的正方体都贡献了4个侧表面积
                    area += val * 4 + 2;
                    // 减掉 i 与 i-1 相贴的两份表面积
                    area -= i > 0 ? Math.min(grid[i - 1][j], val) * 2 : 0;
                    // 减掉 j 与 j-1 相贴的两份表面积
                    area -= j > 0 ? Math.min(grid[i][j - 1], val) * 2 : 0;
                }
            }
        }

        return area;
    }
}
