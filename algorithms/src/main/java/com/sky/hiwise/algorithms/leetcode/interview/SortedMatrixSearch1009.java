package com.sky.hiwise.algorithms.leetcode.interview;

public class SortedMatrixSearch1009 {

    /**
     * 面试题 10.09. 排序矩阵查找
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     *
     * 示例:
     *
     * 现有矩阵 matrix 如下：
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     *
     * 给定 target = 20，返回 false。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m==0) {
            return false;
        }
        int n = matrix[0].length;
        int x = m - 1;
        int y = 0;
        while(x >= 0 && x < m && y >= 0 && y < n) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                x--;
                continue;
            }
            if (matrix[x][y] < target) {
                y++;
                continue;
            }
        }
        return false;
    }
}
