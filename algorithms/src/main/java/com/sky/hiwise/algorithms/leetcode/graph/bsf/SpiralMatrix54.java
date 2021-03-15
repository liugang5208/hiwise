package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix54 {
    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = 0, col = 0, total = m * n, dirIdx = 0;
        for (int i = 0; i < total; i++) {
            ans.add(matrix[row][col]);
            visited[row][col] = true;
            int nextRow = row + directions[dirIdx][0], nextCol = col + directions[dirIdx][1];
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]) {
                dirIdx = (dirIdx + 1) % 4;
            }
            row += directions[dirIdx][0];
            col += directions[dirIdx][1];
        }
        return ans;
    }

}
