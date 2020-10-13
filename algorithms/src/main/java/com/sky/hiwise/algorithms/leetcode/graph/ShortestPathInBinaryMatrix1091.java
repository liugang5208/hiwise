package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix1091 {

    /**
     * 1091. 二进制矩阵中的最短路径
     * 在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
     * 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
     * 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
     * C_1 位于 (0, 0)（即，值为 grid[0][0]）
     * C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
     * 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
     * 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
     * 示例 1：
     * 输入：[[0,1],[1,0]]
     * 输出：2
     * 示例 2：
     * 输入：[[0,0,0],[1,1,0],[1,1,0]]
     * 输出：4
     * 提示：
     * 1 <= grid.length == grid[0].length <= 100
     * grid[i][j] 为 0 或 1
     * @param grid
     * @return
     */
    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
            {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private int R;
    private int C;
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null) {
            return -1;
        }
        R = grid.length;
        C = grid[0].length;
        if(grid[0][0] == 1) return -1;
        boolean[][] visited = new boolean[R][C];
        int[][] dis = new int[R][C];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0][0] = true;
        dis[0][0] = 1;
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int curX = cur / C;
            int curY = cur % C;
            for (int d = 0; d < 8; d++) {
                int nextX = curX + dirs[d][0];
                int nextY = curY + dirs[d][1];
                if (inArea(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] == 0) {
                    visited[nextX][nextY] = true;
                    queue.add(nextX * C + nextY);
                    dis[nextX][nextY] = dis[curX][curY] + 1;
                    if (nextX == R - 1 && nextY == C - 1) {
                        return dis[nextX][nextY];
                    }
                }
            }
        }
        return dis[R - 1][C - 1] == 0 ? -1 : dis[R - 1][C - 1];
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] grid = {{0}};
        System.out.println((new ShortestPathInBinaryMatrix1091()).shortestPathBinaryMatrix(grid));
    }
}
