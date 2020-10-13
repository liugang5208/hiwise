package com.sky.hiwise.algorithms.leetcode.graph;

public class GameLife289 {

    /**
     * 289. 生命游戏
     * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     *
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     *
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
     *
     *
     * @param board
     */
    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
            {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    int R, C;
    public void gameOfLife(int[][] board) {
        R = board.length;
        C = board[0].length;
        int[][] board1 = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                board1[r][c] = board[r][c];
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int scanSum = scan(r, c, board1);
                if (board1[r][c] == 1 && (scanSum < 2 || scanSum > 3)) {
                    board[r][c] = 0;
                }
                if (board1[r][c] == 0 && scanSum == 3) {
                    board[r][c] = 1;
                }
            }
        }
    }

    private int scan(int x, int y, int[][] board1) {
        int sum = 0;
        for (int d = 0; d < 8; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            if (inArea(nextX, nextY) && board1[nextX][nextY] == 1) {
                sum++;
            }
        }
        return sum;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
