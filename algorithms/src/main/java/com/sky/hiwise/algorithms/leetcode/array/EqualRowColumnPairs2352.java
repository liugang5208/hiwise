package com.sky.hiwise.algorithms.leetcode.array;

/**
 * @date: 2023-06-06 10:56
 **/
public class EqualRowColumnPairs2352 {

    /**
     * 2352. 相等行列对
     * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
     * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
     * 示例 1：
     * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
     * 输出：1
     * 解释：存在一对相等行列对：
     * - (第 2 行，第 1 列)：[2,7,7]
     */
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check(grid, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    public boolean check(int[][] grid, int i, int j) {
        int len = grid.length;
        for (int idx = 0; idx < len; idx ++) {
            if (grid[i][idx] != grid[idx][j]) {
                return false;
            }
        }
        return true;
    }
}
