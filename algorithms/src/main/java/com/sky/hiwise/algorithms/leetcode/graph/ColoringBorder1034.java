package com.sky.hiwise.algorithms.leetcode.graph;

public class ColoringBorder1034 {

    /**
     * 1034. 边框着色
     * 给出一个二维整数网格 grid，网格中的每个值表示该位置处的网格块的颜色。
     * 只有当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一连通分量。
     * 连通分量的边界是指连通分量中的所有与不在分量中的正方形相邻（四个方向上）的所有正方形，
     * 或者在网格的边界上（第一行/列或最后一行/列）的所有正方形。
     * 给出位于 (r0, c0) 的网格块和颜色 color，使用指定颜色 color 为所给网格块的连通分量的边界进行着色，
     * 并返回最终的网格 grid 。
     * 示例 1：
     * 输入：grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
     * 输出：[[3, 3], [3, 2]]
     * 示例 2：
     * 输入：grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
     * 输出：[[1, 3, 3], [2, 3, 3]]
     * 示例 3：
     * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
     * 输出：[[2, 2, 2], [2, 1, 2], [2, 2, 2]]
     * 提示：
     * 1 <= grid.length <= 50
     * 1 <= grid[0].length <= 50
     * 1 <= grid[i][j] <= 1000
     * 0 <= r0 < grid.length
     * 0 <= c0 < grid[0].length
     * 1 <= color <= 1000
     * @param grid
     * @param r0
     * @param c0
     * @param color
     * @return
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean[][] visited;
    int R, C;
    int[][] grid;
    int origColor;
    int[][] res;
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        R = grid.length;
        C = grid[0].length;
        if (r0 >= R || c0 >= C) {
            return grid;
        }
        visited = new boolean[R][C];
        origColor = grid[r0][c0];
        this.grid = res = grid;
        dfs(r0, c0, color);
        return res;
    }

    private void dfs(int x, int y, int color) {
        if (x == 0 || y == 0 || x == R - 1 || y == C - 1) {
            res[x][y] = color;
        }
        visited[x][y] = true;
        for(int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            if (inArea(nextX, nextY) && !visited[nextX][nextY]) {
                if (grid[nextX][nextY] != origColor) {
                    res[x][y] = color;
                    continue;
                }
                dfs(nextX, nextY, color);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,1},{1,1,1},{1,1,1}};
        int[][] res = (new ColoringBorder1034()).colorBorder(grid, 0, 0, 2);
        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < res[0].length; j++) {
                System.out.println(res[i][j]);
            }
        }
    }
}
