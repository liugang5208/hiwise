package com.sky.hiwise.algorithms.leetcode.graph.dfs;

public class NumClosedIslands1254 {
    /**
     * 1254. 统计封闭岛屿的数目
     * 有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0 ）要么是水域（记号为 1 ）。
     * 我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。
     * 如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。
     * 请返回封闭岛屿的数目。
     * 示例 1：
     * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
     * 输出：2
     * 解释：
     * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
     * 示例 2：
     * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
     * 输出：1
     * 示例 3：
     * 输入：grid = [[1,1,1,1,1,1,1],
     *              [1,0,0,0,0,0,1],
     *              [1,0,1,1,1,0,1],
     *              [1,0,1,0,1,0,1],
     *              [1,0,1,1,1,0,1],
     *              [1,0,0,0,0,0,1],
     *              [1,1,1,1,1,1,1]]
     * 输出：2
     * 提示：
     *
     * 1 <= grid.length, grid[0].length <= 100
     * 0 <= grid[i][j] <=1
     */
    int R, C;
    int[][] grid;
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int closedIsland(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        int ans = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 0) {
                    ans += dfs(r, c);
                }
            }
        }
        return ans;
    }

    private int dfs(int r, int c) {
        if (!inArea(r, c)) {
            return 0;
        }
        if (grid[r][c] == 1) {
            return 1;
        }
        grid[r][c] = 1;
        int ret = 1;
        for (int d = 0; d < 4; d++) {
            ret = Math.min(ret, dfs(r + dirs[d][0], c + dirs[d][1]));
        }
        return ret;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }


}
