package com.sky.hiwise.algorithms.leetcode.graph;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class UniquePaths980 {
    /**
     * 980. 不同路径 III
     * 在二维网格 grid 上，有 4 种类型的方格：
     * 1 表示起始方格。且只有一个起始方格。
     * 2 表示结束方格，且只有一个结束方格。
     * 0 表示我们可以走过的空方格。
     * -1 表示我们无法跨越的障碍。
     * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，
     * 每一个无障碍方格都要通过一次。
     * 示例 1：
     * 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
     * 输出：2
     * 解释：我们有以下两条路径：
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
     * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
     * 示例 2：
     * 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
     * 输出：4
     * 解释：我们有以下四条路径：
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
     * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
     * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
     * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
     * 示例 3：
     * 输入：[[0,1],[2,0]]
     * 输出：0
     * 解释：
     * 没有一条路能完全穿过每一个空的方格一次。
     * 请注意，起始和结束方格可以位于网格中的任意位置。
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean[][] visited;
    int start,end;
    int R, C;
    int[][] grid;

    public int uniquePathsIII(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        this.grid = grid;
        visited = new boolean[R][C];
        int left = R * C;

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    start = r * C + c;
                    grid[r][c] = 0;
                } else if (grid[r][c] == 2) {
                    end = r * C + c;
                    grid[r][c] = 0;
                } else if (grid[r][c] == -1) {
                    left --;
                }
            }
        }
        return dfs(start, left);
    }

    private int dfs(int v, int left) {
        int x = v / C;
        int y = v % C;
        visited[x][y] = true;
        left --;
        if (left == 0 && v == end) {
            visited[x][y] = false;  //需要找到所有可行的路径
            return 1;
        }
        int res = 0;
        for (int d = 0; d < 4; d++) {
            int newX = x + dirs[d][0];
            int newY = y + dirs[d][1];
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] == 0) {
                res += dfs(newX * C + newY, left);
            }
        }
        visited[x][y] = false;
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        //LinkedBlockingQueue
        //ThreadPoolExecutor
        //LinkedList
    }
}
