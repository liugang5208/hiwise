package com.sky.hiwise.algorithms.leetcode.dynamic;

import java.util.Arrays;

public class OnesZeroes474 {

    /**
     * 474. 一和零
     * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
     * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
     * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
     * 注意:
     * 给定 0 和 1 的数量都不会超过 100。
     * 给定字符串数组的长度不会超过 600。
     * 示例 1:
     * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
     * 输出: 4
     * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
     * 示例 2:
     * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
     * 输出: 2
     * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int dp[][] = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], 0);
        }
        for(int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int numsOf0 = 0;
            int numsOf1 = 0;
            for(int j = 0; j < str.length(); j ++) {
                if (str.charAt(j) == '0') {
                    numsOf0 ++;
                } else {
                    numsOf1 ++;
                }
            }

            for(int p = m; p >= numsOf0; p-- ) {
                for(int q = n; q >= numsOf1; q--) {
                    dp[p][q] = Math.max(dp[p][q], dp[p - numsOf0][q - numsOf1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 首先这是一个复杂一点的背包问题，m个0，n个1 可以看作是背包，而字符串数组strs是物品列表
     * 则对于每一个物品(str)，都有放进背包(背包的容量要变成m-numsOfStr0,n-numsOfStr1)和不放进背包两种选择,其中numsOfStr0表示str中0的个数，numsOfStr1表示str中1的个数
     * 则有
     * 状态: f(i,j,k)代表用j个0，k个1组装strs[0...i]的最大个数
     * 动态转移方程: f(i,j,k) = max(f(i-1,j,k),f(i-1,j-numsOfStr0,k-numsOfStr1))
     *
     * 物品（数组中的字符串）；背包限制（0,1数量）
     * 因为背包有两个限制，所以是二维背包，需要通过二维数组进行实现。
     * 即：dp[i][j]=max(dp[i][j],dp[i-0数量][j-1数量]+1)；表示[0:i-1]的字符串物品中，j个0，k个1最多能够构成字符串数量。
     */
}
