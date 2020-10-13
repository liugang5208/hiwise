package com.sky.hiwise.algorithms.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueens52 {

    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    public int totalNQueens(int n) {
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        return putQueen(n, 0, 0);
    }

    // 尝试在一个n皇后问题中, 摆放第index行的皇后位置
    private int putQueen(int n, int index, int count) {

        for(int i = 0; i < n; i++) {
            if (!col[i] && !dia1[i + index] && !dia2[index - i + n - 1]) {
                dia2[index - i + n - 1] = dia1[i + index] = col[i] = true;
                if (index + 1 == n) {
                    count ++;
                } else {
                    count = putQueen(n, index + 1, count);
                }
                dia2[index - i + n - 1] = dia1[i + index] = col[i] = false;
            }
        }
        return count;
    }
}
