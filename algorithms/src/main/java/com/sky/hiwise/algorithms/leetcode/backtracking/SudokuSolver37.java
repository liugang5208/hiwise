package com.sky.hiwise.algorithms.leetcode.backtracking;

public class SudokuSolver37 {

    /**
     * 37. 解数独
     * 编写一个程序，通过已填充的空格来解决数独问题。
     * 一个数独的解法需遵循如下规则：
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     * 空白格用 '.' 表示。
     * Note:
     * 给定的数独序列只包含数字 1-9 和字符 '.' 。
     * 你可以假设给定的数独只有唯一解。
     * 给定数独永远是 9x9 形式的。
     * @param board
     */
    private boolean[][] rowUsed;
    private boolean[][] colUsed;
    private boolean[][][] boxUsed;
    public void solveSudoku(char[][] board) {
        int len = board.length;
        rowUsed = new boolean[len][len + 1];
        colUsed = new boolean[len][len + 1];
        boxUsed = new boolean[len / 3][len / 3][len + 1];
        // 初始化
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++) {
                int num = board[row][col] - '0';
                if(1 <= num && num <= 9){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;
                }
            }
        }
        recusiveSolveSudoku(board, 0, 0);
    }

    private boolean recusiveSolveSudoku(char[][] board, int row, int col) {
        if (col == board[0].length) {
            col = 0;
            row ++;
            if (row == board.length) {
                return true;
            }
        }
        if (board[row][col] == '.') {
            for(int num = 1; num <= 9; num++) {
                boolean canUse = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row / 3][col / 3][num]);
                if (canUse) {
                    rowUsed[row][num] = colUsed[col][num] = boxUsed[row / 3][col / 3][num] = true;
                    board[row][col] = (char) (num + '0');
                    if (recusiveSolveSudoku(board, row, col + 1)) {
                        return true;
                    }
                    rowUsed[row][num] = colUsed[col][num] = boxUsed[row / 3][col / 3][num] = false;
                    board[row][col] = '.';
                }
            }
        } else {
            return recusiveSolveSudoku(board, row, col + 1);
        }
        return false;
    }
}
