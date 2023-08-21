package com.sky.hiwise.algorithms.leetcode.graph.bsf;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @date: 2023-06-21 11:13
 **/
public class LCP41 {

    /**
     * LCP 41. 黑白翻转棋
     * 在 n*m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。
     * 当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
     * 「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 chessboard。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。
     * 注意：
     * 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 继续 翻转白棋
     * 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
     * 输入：chessboard = ["....X.","....X.","XOOO..","......","......"]
     * 输出：3
     * 解释：
     * 可以选择下在 [2,4] 处，能够翻转白方三枚棋子。
     * @param chessboard
     * @return
     */
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
    int R, C;
    String[] originBoard;
    public int flipChess(String[] chessboard) {
        R = chessboard.length;
        C = chessboard[0].length();
        originBoard = chessboard;
        int res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (chessboard[i].charAt(j) == '.') {
                    res = Math.max(res, bfs(i, j));
                }
            }
        }
        return res;
    }

    private int bfs(int i, int j) {
        int cnt = 0;
        char[][] board = new char[R][C];
        for (int k = 0; k < R; k++) {
            board[k] = originBoard[k].toCharArray();
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        board[i][j] = 'X';
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            for (int[] dir : dirs) {
                if (judge(board, x, y, dir[0], dir[1])) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];
                    while (board[nextX][nextY] != 'X') {
                        queue.add(new int[]{nextX, nextY});
                        board[nextX][nextY] = 'X';
                        nextX += dir[0];
                        nextY += dir[1];
                        ++cnt;
                    }
                }
            }
        }
        return cnt;
    }

    private boolean judge(char[][] board, int i, int j, int di, int dj) {
        i += di;
        j += dj;
        while (inArea(i, j)) {
            if (board[i][j] == 'X') {
                return true;
            } else if (board[i][j] == '.') {
                return false;
            }
            i += di;
            j += dj;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        String[] chessboard = new String[]{".X.",".O.","XO."};
        System.out.println(new LCP41().flipChess(chessboard));
    }

}
