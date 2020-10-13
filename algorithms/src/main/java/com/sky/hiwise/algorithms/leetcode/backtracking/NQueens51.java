package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueens51 {

    /**
     * 51. N皇后
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 上图为 8 皇后问题的一种解法。
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * 示例:
     * 输入: 4
     * 输出: [
     *  [".Q..",  // 解法 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // 解法 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    private List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        res = new ArrayList<>();
        LinkedList<Integer> row = new LinkedList<>();
        putQueen(n, 0, row);
        return res;
    }

    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置
    private void putQueen(int n, int index, LinkedList<Integer> row) {
        if(index == n){
            res.add(generateBoard(n, row));
            return;
        }
        for(int i = 0; i < n; i++) {
            if (!col[i] && !dia1[i + index] && !dia2[index - i + n - 1]) {
                dia2[index - i + n - 1] = dia1[i + index] = col[i] = true;
                row.addLast(i);
                putQueen(n, index + 1, row);
                dia2[index - i + n - 1] = dia1[i + index] = col[i] = false;
                row.removeLast();
            }
        }
    }

    private ArrayList<String> generateBoard(int n, LinkedList<Integer> row) {
        ArrayList<String> board = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[row.get(i)] = 'Q';
            board.add(new String(charArray));
        }
        return board;
    }
}
