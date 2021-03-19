package com.sky.hiwise.algorithms.leetcode.dynamic;

public class DistinctSubsequences115 {
    /**
     * 115. 不同的子序列
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     * 题目数据保证答案符合 32 位带符号整数范围。
     * 示例 1：
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * 解释：
     * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
     * (上箭头符号 ^ 表示选取的字母)
     * rabbbit
     * ^^^^ ^^
     * rabbbit
     * ^^ ^^^^
     * rabbbit
     * ^^^ ^^^
     * 示例 2：
     * 输入：s = "babgbag", t = "bag"
     * 输出：5
     * 解释：
     * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
     * (上箭头符号 ^ 表示选取的字母)
     * babgbag
     * ^^ ^
     * babgbag
     * ^^    ^
     * babgbag
     * ^    ^^
     * babgbag
     *   ^  ^^
     * babgbag
     *     ^^^
     * 提示：
     * 0 <= s.length, t.length <= 1000
     * s 和 t 由英文字母组成
     */
    public int numDistinct(String s, String t) {
        int m = t.length(), n = s.length();
        if (m > n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "rabbbit";

        String t = "rabbit";
        System.out.println((new DistinctSubsequences115()).numDistinct(s, t));
    }

    /**
     * 动态规划
     * dp[i][j] 代表 T 前 i 字符串可以由 S j 字符串组成最多个数.
     * 所以动态方程:
     * 当 S[j] == T[i] , dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
     * 当 S[j] != T[i] , dp[i][j] = dp[i][j-1]
     * 举个例子,如示例的
     * 对于第一行, T 为空,因为空集是所有字符串子集, 所以我们第一行都是 1
     * 对于第一列, S 为空,这样组成 T 个数当然为 0` 了
     * 至于下面如何进行,大家可以通过动态方程,自行模拟一下!
     * 作者：powcai
     * 链接：https://leetcode-cn.com/problems/distinct-subsequences/solution/dong-tai-gui-hua-by-powcai-5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
