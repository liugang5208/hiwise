package com.sky.hiwise.algorithms.leetcode.backtracking;

public class WordSearch79 {

    /**
     * 79. 单词搜索
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * 示例:
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     * 给定 word = "ABCCED", 返回 true.
     * 给定 word = "SEE", 返回 true.
     * 给定 word = "ABCB", 返回 false.
     * @param board
     * @param word
     * @return
     */
    int[][] path = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m, n;
    boolean isVisited[][];
    public boolean exist(char[][] board, String word) {
        m = board.length;
        if (m <= 0) {
            return false;
        }
        n = board[0].length;
        isVisited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (searchWord(i, j, 0, board, word))
                    return true;
            }
        }
        return false;
    }

    private boolean searchWord(int startX, int startY, int index, char[][] board, String word) {
        if (word.length() - 1  == index) {
            return board[startX][startY] == word.charAt(index);
        }
        if (board[startX][startY] == word.charAt(index)) {
            isVisited[startX][startY] = true;
            for (int i = 0; i < 4; i++) {
                int newX = startX + path[i][0];
                int newY = startY + path[i][1];
                if (inBoard(newX, newY) && !isVisited[newX][newY] && searchWord(newX, newY, index + 1, board, word)) {
                    return true;
                }
            }
            isVisited[startX][startY] = false;
        }
        return false;
    }

    private boolean inBoard(int x, int y) {
        return x >= 0 && x < m && y < n && y >= 0;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println((new WordSearch79()).exist(board, word));
    }
}
