package com.sky.hiwise.algorithms.leetcode.dynamic;

public class TwoKeysKeyboard650 {
    /**
     * 650. 只有两个键的键盘
     * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
     * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
     * Paste（粘贴）：粘贴 上一次 复制的字符。
     * 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。
     * 示例 1：
     * 输入：3
     * 输出：3
     * 解释：
     * 最初, 只有一个字符 'A'。
     * 第 1 步, 使用 Copy All 操作。
     * 第 2 步, 使用 Paste 操作来获得 'AA'。
     * 第 3 步, 使用 Paste 操作来获得 'AAA'。
     * 示例 2：
     * 输入：n = 1
     * 输出：0
     */
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                    dp[i] = Math.min(dp[i], dp[i / j] + j);
                }
            }
        }
        return dp[n];
    }
    /**
     * 因此，这里的 j 必须是i 的因数，「粘贴」操作的使用次数即为 i/j−1。我们可以枚举j 进行状态转移，这样就可以得到状态转移方程：
     * fi = min(fj + i/j)
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/2-keys-keyboard/solution/zhi-you-liang-ge-jian-de-jian-pan-by-lee-ussa/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
