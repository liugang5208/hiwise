package com.sky.hiwise.algorithms.leetcode.graph;

public class SurroundedRegions130 {

    /**
     * 130. 被围绕的区域
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * 示例:
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
     * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
     * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     * @param board
     */
    int[][] path = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int m, n;
    public void solve(char[][] board) {
        m = board.length;
        if (m == 0) {
            return;
        }
        n = board[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                boolean isEdge = (i == 0 || j == 0 || i == m - 1 || j == n - 1);
                //从边界出发 将所有与边界连通的O 全部替换成#
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        //最后只需要把 所有的O 反转成X， 再把之前与边界连通的O替换成#的 位置再次替换回O即可
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int startX, int startY) {
        if (!inBoard(startX, startY) || board[startX][startY] == 'X' || board[startX][startY] == '#') {
            return;
        }
        board[startX][startY] = '#';
        for(int i = 0; i < 4; i++) {
            int newX = startX + path[i][0];
            int newY = startY + path[i][1];
            dfs(board, newX, newY);
        }
    }

    private boolean inBoard(int x, int y) {
        return x >= 0 && x < m && y < n && y >= 0;
    }

    /**
     * 思路：
     * 这道题我们拿到基本就可以确定是图的 dfs、bfs 遍历的题目了。
     * 题目中解释说被包围的区间不会存在于边界上，所以我们会想到边界上的 O 要特殊处理，只要把边界上的 O 特殊处理了，那么剩下的 O 替换成 X 就可以了。
     * 问题转化为，如何寻找和边界联通的 O，我们需要考虑如下情况。
     * X X X X
     * X O O X
     * X X O X
     * X O O X
     * 这时候的 O 是不做替换的。因为和边界是连通的。
     * 为了记录这种状态，我们把这种情况下的 O 换成 # 作为占位符，待搜索结束之后，遇到 O 替换为 X（和边界不连通的 O）；遇到 #，替换回 O(和边界连通的 O)。
     * 链接：https://leetcode-cn.com/problems/surrounded-regions/solution/bfsdi-gui-dfsfei-di-gui-dfsbing-cha-ji-by-ac_pipe/
     * 如何寻找和边界联通的O? 从边界出发，对图进行 dfs 和 bfs 即可。这里简单总结下 dfs 和 dfs。
     * bfs 递归。可以想想二叉树中如何递归的进行层序遍历。
     * bfs 非递归。一般用队列存储。
     * dfs 递归。最常用，如二叉树的先序遍历。
     * dfs 非递归。一般用 stack。
     */
}
