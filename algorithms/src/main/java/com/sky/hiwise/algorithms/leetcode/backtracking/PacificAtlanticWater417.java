package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PacificAtlanticWater417 {

    /**
     * 417
     * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
     * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
     *
     * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
     *
     * Note:
     *
     * The order of returned grid coordinates does not matter.
     * Both m and n are less than 150.
     *
     *
     * Example:
     *
     * Given the following 5x5 matrix:
     *
     *   Pacific ~   ~   ~   ~   ~
     *        ~  1   2   2   3  (5) *
     *        ~  3   2   3  (4) (4) *
     *        ~  2   4  (5)  3   1  *
     *        ~ (6) (7)  1   4   5  *
     *        ~ (5)  1   1   2   4  *
     *           *   *   *   *   * Atlantic
     *
     * Return:
     *
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
     * @param matrix
     * @return
     */
    int[][] path = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m, n;
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        m = matrix.length;
        if (m == 0) {
            return res;
        }
        n = matrix[0].length;

        // pacific
        boolean[][] pac = new boolean[m][n];
        for(int i = 0; i < m; i ++) {
            dfs(i, 0, matrix, pac, 0);
        }
        for (int j = 0; j < n; j ++) {
            dfs(0, j, matrix, pac, 0);
        }

        // atlantic
        boolean[][] atl = new boolean[m][n];
        for(int i = 0; i < m; i ++) {
            dfs(i, n - 1, matrix, atl, 0);
        }
        for (int j = 0; j < n; j ++) {
            dfs(m - 1, j, matrix, atl, 0);
        }

        for(int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (pac[i][j] && atl[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int x, int y, int[][] matrix, boolean isVisited[][], int pre) {

        if (!inMatrix(x, y) || isVisited[x][y] || matrix[x][y] < pre) {
           return;
        }
        isVisited[x][y] = true;
        for(int i = 0; i < 4; i ++) {
            dfs(x + path[i][0], y + path[i][1], matrix, isVisited, matrix[x][y]);
        }
    }

    private boolean inMatrix(int x, int y) {
        return x >= 0 && x < m && y < n && y >= 0;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println((new PacificAtlanticWater417()).pacificAtlantic(matrix));
    }

}
