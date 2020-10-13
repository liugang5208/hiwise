package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix542 {
    /**
     * 542. 01 矩阵
     * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     * 两个相邻元素间的距离为 1 。
     * 示例 1:
     * 输入:
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 输出:
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 示例 2:
     * 输入:
     * 0 0 0
     * 0 1 0
     * 1 1 1
     * 输出:
     * 0 0 0
     * 0 1 0
     * 1 2 1
     * 注意:
     * 给定矩阵的元素个数不超过 10000。
     * 给定矩阵中至少有一个元素是 0。
     * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R;
    private int C;
    public int[][] updateMatrix(int[][] matrix) {
        R = matrix.length;
        C = matrix[0].length;
        int[][] ans = new int[R][C];
        for (int k = 0; k < R; k++) {
            Arrays.fill(ans[k], Integer.MAX_VALUE);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0 ; i < R; i++) {
            for (int j = 0 ; j < C; j++) {
                if (matrix[i][j] == 0) {
                    ans[i][j] = 0;
                    queue.add(i * C + j);
                }
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            int currX = curr / C, currY = curr % C;
            for (int d = 0; d < 4; d++) {
                int newX = currX + dirs[d][0];
                int newY = currY + dirs[d][1];
                if (inArea(newX, newY)) {
                    if (ans[newX][newY] > ans[currX][currY] + 1) {
                        ans[newX][newY] = ans[currX][currY] + 1;
                        queue.add(newX * C + newY);
                    }
                }
            }
        }
        return ans;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
