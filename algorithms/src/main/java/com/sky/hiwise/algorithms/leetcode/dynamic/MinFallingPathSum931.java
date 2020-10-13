package com.sky.hiwise.algorithms.leetcode.dynamic;

public class MinFallingPathSum931 {

    /**
     * 931. 下降路径最小和
     * 给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
     * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。
     * 在下一行选择的元素和当前行所选元素最多相隔一列。
     * 示例：
     * 输入：[[1,2,3],[4,5,6],[7,8,9]]
     * 输出：12
     * 解释：
     * 可能的下降路径有：
     * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
     * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
     * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
     * 和最小的下降路径是 [1,4,7]，所以答案是 12。
     * @param A
     * @return
     */
    public int minFallingPathSum(int[][] A) {
        int rows = A.length;
        if (rows == 0) {
            return 0;
        }
        int cols = A[0].length;
        if (cols == 0) {
            return 0;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j==0) {
                    A[i][j] = Math.min(A[i][j] + A[i-1][j], A[i][j] + A[i-1][j+1]);
                } else if (j == rows - 1) {
                    A[i][j] = Math.min(A[i][j] + A[i-1][j], A[i][j] + A[i-1][j-1]);
                } else {
                    A[i][j] = Math.min(A[i][j] + A[i-1][j-1], Math.min(A[i][j] + A[i-1][j], A[i][j] + A[i-1][j+1]));
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cols; i++) {
            ans = Math.min(ans, A[rows-1][i]);
        }
        return ans;
    }
}
