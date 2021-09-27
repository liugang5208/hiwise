package com.sky.hiwise.algorithms.leetcode.dynamic;

public class DeleteOperationTwoStrings583 {
    /**
     * 583. 两个字符串的删除操作
     * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
     * 示例：
     * 输入: "sea", "eat"
     * 输出: 2
     * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     * 提示：
     * 给定单词的长度不超过500。
     * 给定单词中的字符只含有小写字母。
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m - dp[m][n] + n - dp[m][n];
    }

    public int minDistance1(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for(int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
    /**
     * 序列 DP
     * 上述解决方案是套用了「最长公共子序列（LCS）」进行求解，最后再根据 LCS 长度计算答案。
     * 而更加契合题意的状态定义是根据「最长公共子序列（LCS）」的原始状态定义进行微调：定义 f[i][j]代表考虑 s1 的前i个字符、考虑s2的前j个字符（最终字符串不一定包含s1[i]或s2[j]）时形成相同字符串的最小删除次数。
     * 同理，不失一般性的考虑 f[i][j]f[i][j] 该如何计算：
     * s1[i]==s2[j]：f[i][j] = f[i - 1][j - 1]f[i][j]=f[i−1][j−1]，代表可以不用必然删掉 s1[i] 和 s2[j] 形成相同字符串；
     * s1[i]!=s2[j]：f[i][j] = \min(f[i - 1][j] + 1, f[i][j - 1] + 1)f[i][j]=min(f[i−1][j]+1,f[i][j−1]+1)，代表至少一个删除 s1[i]s1[i] 和 s2[j]s2[j] 中的其中一个。
     * f[i][j]f[i][j] 为上述方案中的最小值，最终答案为 f[n][m]f[n][m]。
     * 作者：AC_OIer
     * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings/solution/gong-shui-san-xie-cong-liang-chong-xu-li-wqv7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
