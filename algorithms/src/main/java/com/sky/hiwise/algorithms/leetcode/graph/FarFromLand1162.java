package com.sky.hiwise.algorithms.leetcode.graph;


import java.util.LinkedList;
import java.util.Queue;

public class FarFromLand1162 {

    /**
     * 1162. 地图分析
     * 你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。
     * 其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？
     * 请返回该海洋区域到离它最近的陆地区域的距离。
     *
     * 我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：
     * (x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
     *
     * 如果我们的地图上只有陆地或者海洋，请返回 -1。
     *
     *
     *
     * 示例 1：
     * @param grid
     * @return
     */
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int R, C;
    int[][] grid;
    public int maxDistance(int[][] grid) {
        R = grid.length;
        if (R == 0) {
            return -1;
        }
        C = grid[0].length;
        this.grid = grid;
        int ans = -1;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 0) {
                    ans = Math.max(ans, findBSF(r, c));
                }
            }
        }
        return ans;
    }

    private int findBSF(int r, int c) {
        boolean[][] visited = new boolean[R][C];
        visited[r][c] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(r * C + c);
        while (!queue.isEmpty()){
            int curr = queue.poll();
            int currX = curr / C, currY = curr % C;
            for (int d = 0; d < 4; d++) {
                int nextX = currX + dirs[d][0];
                int nextY = currY + dirs[d][1];
                if (inArea(nextX, nextY) && !visited[nextX][nextY]) {
                    if (grid[nextX][nextY] == 0) {
                        queue.add(nextX * C + nextY);
                    } else {
                        return distance(r, c, nextX, nextY);
                    }
                    visited[nextX][nextY] = true;
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private int distance(int x0, int y0, int x1, int y1) {
        return Math.abs((x0 - x1)) + Math.abs((y0 - y1));
    }
}
