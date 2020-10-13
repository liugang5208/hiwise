package com.sky.hiwise.algorithms.leetcode.graph;

public class IslandsNumber200 {

    /**
     * 200. 岛屿数量
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
     * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
     * 你可以假设网格的四个边均被水包围。
     * 示例 1:
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     * 输出: 1
     * 示例 2:
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * 输出: 3
     */
    int[][] path = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m, n;
    boolean isVisited[][];
    public int numIslands(char[][] grid) {
        int res = 0;
        m = grid.length;
        if (m <= 0) {
            return res;
        }
        n = grid[0].length;
        isVisited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !isVisited[i][j]) {
                    res ++;
                    dfs(i, j, grid);
                }
            }
        }
        return res;
    }

    //从grid[startX][startY]位置开始floodfill
    //保证startX和startY合法且grid[startX][startY]没有被访问过
    private void dfs(int startX, int startY, char[][] grid) {
        isVisited[startX][startY] = true;
        for (int i = 0; i < 4; i++ ) {
           int newX = startX + path[i][0];
           int newY = startY + path[i][1];
           if (inGrid(newX, newY) && grid[newX][newY] == '1' && !isVisited[newX][newY]) {
               dfs(newX, newY, grid);
           }
        }
    }

    private boolean inGrid(int x, int y) {
        return x >= 0 && x < m && y < n && y >= 0;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println((new IslandsNumber200()).numIslands(grid));
    }
}
