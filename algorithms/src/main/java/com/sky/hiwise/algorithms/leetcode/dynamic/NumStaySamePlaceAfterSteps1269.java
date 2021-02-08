package com.sky.hiwise.algorithms.leetcode.dynamic;

public class NumStaySamePlaceAfterSteps1269 {
    /**
     * 1269. 停在原地的方案数
     * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
     * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
     * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
     * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
     * 示例 1：
     * 输入：steps = 3, arrLen = 2
     * 输出：4
     * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
     * 向右，向左，不动
     * 不动，向右，向左
     * 向右，不动，向左
     * 不动，不动，不动
     * 示例  2：
     * 输入：steps = 2, arrLen = 4
     * 输出：2
     * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
     * 向右，向左
     * 不动，不动
     */
    private static int MOD = 1_000_000_007;
    public int numWays(int steps, int arrLen) {
        int[][] dp = new int[steps + 1][steps + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= Math.min(i, arrLen - 1); j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                    dp[i][j] = dp[i][j] % MOD;
                }
                if (j < i) {
                    dp[i][j] += dp[i - 1][j + 1];
                    dp[i][j] = dp[i][j] % MOD;
                }
            }
        }
        return dp[steps][0];
    }
    /**
     * 方法二：动态规划
     * 分析
     * 根据方法一中的递推式，可以采用动态规划的方法解决问题
     * 当我们走第xx步时,假设我们的位置为pospos,那么这个位置只能由在x-1x−1步时,从pos-1或pos或pos+1pos−1或pos或pos+1各走+1, 0, -1+1,0,−1达到。
     * 动规方程： dp(x, pos) = dp(x-1, pos-1) + dp(x-1, pos) + dp(x-1, pos+1)dp(x,pos)
     * 注意：在走xx步时，我们能到达最远的距离是xx和arrLenarrLen中的较小者。
     * 作者：copyreadmachine
     * 链接：https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/solution/java-ji-yi-hua-sou-suo-jian-dan-yi-dong-by-copyrea/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
