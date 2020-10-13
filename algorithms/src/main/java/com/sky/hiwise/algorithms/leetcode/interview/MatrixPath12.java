package com.sky.hiwise.algorithms.leetcode.interview;

public class MatrixPath12 {

    /**
     * 面试题12. 矩阵中的路径
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
     * [["a","b","c","e"],
     * ["s","f","c","s"],
     * ["a","d","e","e"]]
     * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
     * 示例 1：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     * 示例 2：
     * 输入：board = [["a","b"],["c","d"]], word = "abcd"
     * 输出：false
     * @param board
     * @param word
     * @return
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;
    private int R, C;
    private String word;
    private char[][] board;
    private boolean ans;
    public boolean exist(char[][] board, String word) {
        R = board.length;
        C = board[0].length;
        this.word = word;
        visited = new boolean[R][C];
        this.board = board;
        ans = false;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!ans) {
                    dfs(r, c, 0);
                }
            }
        }
        return ans;
    }

    private void dfs(int r, int c, int index) {
        if (index >= word.length()) {
            return;
        }
        visited[r][c] = true;
        if (word.length() - 1 == index && board[r][c] == word.charAt(index)) {
            ans = true;
        }
        for(int d = 0; d < 4; d++) {
            int nextR = r + dirs[d][0];
            int nextC = c + dirs[d][1];
            if (inArea(nextR, nextC) && !visited[nextR][nextC] && board[r][c] == word.charAt(index)) {
                dfs(nextR, nextC, index + 1);
            }
            visited[r][c] = false;
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        char[][] board1 = new char[][]{{'A', 'B'}, {'C', 'D'}};
        String word1 = "ACDB";
        System.out.println((new MatrixPath12()).exist(board1, word1));
    }

    public boolean exist1(char[][] board, String word) {
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if(k == word.length - 1) return true;
        char tmp = board[i][j];
        board[i][j] = '/';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
        board[i][j] = tmp;
        return res;
    }

}
