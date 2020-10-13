package com.sky.hiwise.algorithms.leetcode.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge934 {

    /**
     * 934. 最短的桥
     * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
     * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
     * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）
     * 示例 1：
     * 输入：[[0,1],[1,0]]
     * 输出：1
     * 示例 2：
     * 输入：[[0,1,0],[0,0,0],[0,0,1]]
     * 输出：2
     * 示例 3：
     * 输入：[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
     * 输出：1
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int R;
    public int C;
    public int[][] grid;
    private boolean[][] visited;
    public int shortestBridge(int[][] A) {
        R = A.length;
        C = A[0].length;
        grid = A;
        visited = new boolean[R][C];
        int start = init();
        if (start == -1) {
            return -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.remove();
                int currX = curr / C, currY = curr % C;
                for(int d = 0; d < 4; d++) {
                    int nextX = currX + dirs[d][0];
                    int nextY = currY + dirs[d][1];
                    int next = nextX * C + nextY;
                    if (!inArea(nextX, nextY)) {
                        continue;
                    }
                    if (grid[nextX][nextY] == 1) {
                        return ans;
                    }
                    if (grid[nextX][nextY] == 2) {
                        continue;
                    }
                    grid[nextX][nextY] = 2;
                    queue.add(next);
                }
            }
            ans ++;
        }
        return ans;
    }

    private int init() {
        int start = -1;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if (grid[i][j] == 1) {
                    start = i * C + j;
                    grid[i][j] = 2;
                    dfsColor(i, j);
                    return start;
                }
            }
        }
        return start;
    }

    private void dfsColor(int x, int y) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int newX = x + dirs[d][0];
            int newY = y + dirs[d][1];
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] == 1) {
                grid[newX][newY] = 2;
                dfsColor(newX, newY);
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        //int[][] A = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
        //int[][] A = {{0,1,0},{0,0,0},{0,0,1}};
        //int[][] A = {{0,1},{1,0}};
        int[][] A = {{0,1,0,0,0},{0,1,0,1,1},{0,0,0,0,1},{0,0,0,0,0},{0,0,0,0,0}};
        ShortestBridge934 t = (new ShortestBridge934());
        System.out.println(t.shortestBridge(A));
        for(int i = 0; i < t.R; i++) {
            for(int j = 0; j < t.C; j++) {
                System.out.println(t.grid[i][j]);
            }
        }
    }

}
