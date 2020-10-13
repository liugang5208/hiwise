package com.sky.hiwise.algorithms.leetcode.graph;


import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges994 {

    /**
     * 994. 腐烂的橘子
     * 在给定的网格中，每个单元格可以有以下三个值之一：
     *
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
     *
     * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
     */
    int R, C;
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int orangesRotting(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 2) {
                    queue.add(r * C + c);
                } else if (grid[r][c] == 1) {
                    count ++;
                }
            }
        }

        int level = 0;
        while (count > 0 && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.remove();
                int currX = curr / C, currY = curr % C;
                for(int d = 0; d < 4; d++) {
                    int nextX = currX + dirs[d][0];
                    int nextY = currY + dirs[d][1];
                    if (inArea(nextX, nextY) && grid[nextX][nextY] == 1) {
                        queue.add(nextX * C + nextY);
                        grid[nextX][nextY] = 2;
                        count--;
                    }
                }

            }
            level ++;
        }
       return count == 0 ? level : -1;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }


}
