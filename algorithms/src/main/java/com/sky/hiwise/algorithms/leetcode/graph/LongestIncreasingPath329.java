package com.sky.hiwise.algorithms.leetcode.graph;

public class LongestIncreasingPath329 {

    /**
     * 329. 矩阵中的最长递增路径
     * 给定一个整数矩阵，找出最长递增路径的长度。
     *
     * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
     *
     * 示例 1:
     *
     * 输入: nums =
     * [
     *   [9,9,4],
     *   [6,6,8],
     *   [2,1,1]
     * ]
     * 输出: 4
     * 解释: 最长递增路径为 [1, 2, 6, 9]。
     * 示例 2:
     *
     * 输入: nums =
     * [
     *   [3,4,5],
     *   [3,2,6],
     *   [2,2,1]
     * ]
     * 输出: 4
     * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
     */
    int R, C, ans = 0;
    int[][] grid, memo;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
           return 0;
        }
        R = matrix.length;
        C = matrix[0].length;
        this.grid = matrix;
        memo = new int[R][C];
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                //dfs(i, j, 0, new boolean[R][C]);
                ans = Math.max(ans, dfs(i,j));
            }
        }
        return ans;
    }

    public int dfs(int x, int y) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        memo[x][y]++;
        for (int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            if (inArea(nextX, nextY) && grid[nextX][nextY] > grid[x][y]) {
                memo[x][y] = Math.max(memo[x][y], dfs(nextX, nextY) + 1);
            }
        }
        return memo[x][y];
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }


    //有bug通过135个case 特殊情况下超出时间限制
    public void dfs(int x, int y, int len, boolean[][] visited) {
        len++;
        ans = Math.max(ans, len);
        for (int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            visited[x][y] = true;
            if (inArea(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] > grid[x][y]) {
                dfs(nextX, nextY, len, visited);
            }
            visited[x][y] = false;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{7,8,9},{9,7,6},{7,2,3}};
        System.out.println((new LongestIncreasingPath329()).longestIncreasingPath(matrix));
    }
}
