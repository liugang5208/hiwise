package com.sky.hiwise.algorithms.leetcode.graph;

public class MakingLargeIsland827 {

    /**
     * 827. 最大人工岛
     * 在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。
     * 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）
     * 示例 1:
     * 输入: [[1, 0], [0, 1]]
     * 输出: 3
     * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
     * 示例 2:
     * 输入: [[1, 1], [1, 0]]
     * 输出: 4
     * 解释: 将一格0变成1，岛屿的面积扩大为 4。
     * 示例 3:
     * 输入: [[1, 1], [1, 1]]
     * 输出: 4
     * 解释: 没有0可以让我们变成1，面积依然为 4。
     * @param grid
     * @return
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R;
    private int C;
    private int[][] grid;
    public int largestIsland(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        this.grid = grid;
        int ans = 0;
        boolean hasZero = false;
        for (int r = 0; r < R; r++) {
           for(int c = 0; c < C; c++) {
               if (grid[r][c] == 0) {
                   hasZero = true;
                   grid[r][c] = 1;
                   boolean[][] visited = new boolean[R][C];
                   ans = Math.max(ans, check(visited, r, c));
                   grid[r][c] = 0;
               }
           }
        }

        return hasZero ? ans : R * C;
    }

    private int check(boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        int ans = 1;
        for (int d = 0; d < 4; d++) {
            int newX = x + dirs[d][0];
            int newY = y + dirs[d][1];
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] == 1) {
                ans += check(visited, newX, newY);
            }
        }
        return ans;
    }


    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
