package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix59 {

    /**
     * 59. 螺旋矩阵 II
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * 示例 1：
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     * 示例 2：
     * 输入：n = 1
     * 输出：[[1]]
     * 提示：
     * 1 <= n <= 20
     * 通过次数74,168提交次数93,216
     */
    public int[][] generateMatrix(int n) {
        int total = n * n, row = 0, col = 0, dirIdx = 0, currNum = 1;
        List<Integer> ans = new ArrayList<>();
        int[][] matrix = new int[n][n];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (currNum <= total) {
            matrix[row][col] = currNum;
            currNum ++;
            int nextRow = row + directions[dirIdx][0], nextCol = col + directions[dirIdx][1];
            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || matrix[nextRow][nextCol] != 0) {
                dirIdx = (dirIdx + 1) % 4;
            }
            row += directions[dirIdx][0];
            col += directions[dirIdx][1];
        }
        return matrix;
    }
}
