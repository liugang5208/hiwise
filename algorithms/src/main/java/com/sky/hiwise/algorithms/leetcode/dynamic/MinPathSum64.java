package com.sky.hiwise.algorithms.leetcode.dynamic;

public class MinPathSum64 {

    /**
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * 示例:
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小。
     * @param grid
     * @return
     */
    int m, n;
    int[][] meno;
    public int minPathSum(int[][] grid) {

        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;
        meno = new int[m][n];
        return calculate(grid, 0, 0);
    }

    public int calculate(int[][] grid, int i, int j) {
        if (i == m || j == n) return Integer.MAX_VALUE;
        if (i == m - 1 && j == n - 1) {
            return grid[i][j];
        }
        if (meno[i][j] > 0) {
            return meno[i][j];
        }
        meno[i][j] = grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
        return meno[i][j];
    }
    /**
     * 暴力就是利用递归，对于每个元素我们考虑两条路径，向右走和向下走，在这两条路径中挑选路径权值和较小的一个。
     * cost(i,j)=grid[i][j]+min(cost(i+1,j),cost(i,j+1))
     */

    /**
     * 方法 2：二维动态规划
     * 算法
     * 我们新建一个额外的 dp 数组，与原矩阵大小相同。在这个矩阵中，dp(i, j) 表示从坐标 (i, j) 到右下角的最小路径权值。
     * 我们初始化右下角的 dp 值为对应的原矩阵值，然后去填整个矩阵，对于每个元素考虑移动到右边或者下面，因此获得最小路径和我们有如下递推公式：
     * dp(i,j)=grid(i,j)+min(dp(i+1,j),dp(i,j+1))
     */
    int[][] dp;
    public int minPathSum2(int[][] grid) {
        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;
        dp = new int[m][n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (i != m - 1 && j == n - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (i != m - 1 && j != n - 1) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i][j + 1], dp[i + 1][j]);
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

    public int minPathSum22(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                else if(i == 0)  grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if(j == 0)  grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    /**
     * 方法 3：一维动态规划
     * 算法
     * 在上个解法中，我们可以用一个一维数组来代替二维数组，dp 数组的大小和行大小相同。
     * 这是因为对于某个固定状态，只需要考虑下方和右侧的节点。
     * 首先初始化 dp 数组最后一个元素是右下角的元素值，然后我们向左移更新每个 dp(j) 为：
     * dp(j)=grid(i,j)+min(dp(j),dp(j+1))
     * 我们对于每一行都重复这个过程，然后向上一行移动，计算完成后 dp(0) 就是最后的结果。
     */
    public int minPathSum3(int[][] grid) {
        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;
        int[] dp = new int[m];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if(i == m - 1 && j != n - 1)
                    dp[j] = grid[i][j] +  dp[j + 1];
                else if(j == n - 1 && i != m - 1)
                    dp[j] = grid[i][j] + dp[j];
                else if(j != n - 1 && i != m - 1)
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                else
                    dp[j] = grid[i][j];
            }
        }
        return dp[0];
    }
}
