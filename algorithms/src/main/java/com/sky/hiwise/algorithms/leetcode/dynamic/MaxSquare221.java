package com.sky.hiwise.algorithms.leetcode.dynamic;

public class MaxSquare221 {

    /**
     * 221. 最大正方形
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     *
     * 示例:
     *
     * 输入:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * 输出: 4
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxLen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    maxLen = Math.max(dp[i][j], maxLen);
                }
            }
        }
        return maxLen * maxLen;
    }
    /**
     * 方法二：动态规划
     * 我们用一个例子来解释这个方法：
     * 0 1 1 1 0
     * 1 1 1 1 1
     * 0 1 1 1 1
     * 0 1 1 1 1
     * 0 0 1 1 1
     * 我们用 0 初始化另一个矩阵 dp，维数和原始矩阵维数相同；
     * dp(i,j) 表示的是由 1 组成的最大正方形的边长；
     * 从(0,0) 开始，对原始矩阵中的每一个 1，我们将当前元素的值更新为
     * dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1
     * 我们还用一个变量记录当前出现的最大边长，这样遍历一次，找到最大的正方形边长 maxsqlen，那么结果就是 maxsqlen^2
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

}
