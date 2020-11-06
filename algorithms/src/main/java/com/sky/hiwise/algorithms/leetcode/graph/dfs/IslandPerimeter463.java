package com.sky.hiwise.algorithms.leetcode.graph.dfs;

public class IslandPerimeter463 {

    /**
     * 463. 岛屿的周长
     * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
     * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     * 示例 :
     * 输入:
     * [[0,1,0,0],
     *  [1,1,1,0],
     *  [0,1,0,0],
     *  [1,1,0,0]]
     * 输出: 16
     * 解释: 它的周长是下面图片中的 16 个黄色的边：
     * @param grid
     * @return
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int R, C;
    int[][] grid;
    public int islandPerimeter(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    ans += dfs(r, c);
                }
            }
        }
        return ans;
    }

    public int dfs(int x, int y) {
        if (!inGrid(x, y) || grid[x][y] == 0) {
            return 1;
        }
        if (grid[x][y] == 2) {
            return 0;
        }
        grid[x][y] = 2;
        int ret = 0;
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            ret += dfs(nextX, nextY);
        }
        return ret;
    }

    private boolean inGrid(int x, int y) {
        return x >= 0 && x < R && y < C && y >= 0;
    }
}
