package com.sky.hiwise.algorithms.leetcode.math;

public class MatrixDiagonalSum1572 {
    /**
     * 1572. 矩阵对角线元素的和
     * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
     * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
     */
    public int diagonalSum(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || (i + j) == n - 1) {
                    sum += mat[i][j];
                }
            }
        }
        return sum;
    }
}
