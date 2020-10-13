package com.sky.hiwise.algorithms.leetcode.string;

public class PalindromePartition132 {
    /**
     * 132. 分割回文串 II
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * 返回符合要求的最少分割次数。
     * 示例:
     * 输入: "aab"
     * 输出: 1
     * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     * @param s
     * @return
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }

        int[] cutDp = new int[len];
        for (int i = 0; i < len; i++) {
            cutDp[i] = i;
        }

        /**
         * dp[i]：表示前缀子串 s[0:i] 分割成若干个回文子串所需要最小分割次数。
         * 如果 s[j + 1, i] 不是回文串，尝试下一个分割边界。
         * 如果 s[j + 1, i] 是回文串，则 dp[i] 就是在 dp[j] 的基础上多一个分割。
         * 于是枚举 j 所有可能的位置，取所有 dp[j] 中最小的再加 1 ，就是 dp[i]。
         * dp[i] = min([dp[j] + 1 for j in range(i) if s[j + 1, i] 是回文])
         */
        // 1 个字符的时候，不用判断，因此 i 从 1 开始
        for (int i = 1; i < len; i++) {
            if (dp[0][i]) {
                cutDp[i] = 0;
                continue;
            }
            // 注意：这里是严格，要保证 s[j + 1:i] 至少得有一个字符串
            for (int j = 0; j < i; j++) {
                if (dp[j + 1][i]) {
                    cutDp[i] = Math.min(cutDp[j] + 1, cutDp[i]);
                }
            }
        }
        return cutDp[len - 1];
    }


    /**
     * 方法一：动态规划
     * 步骤 1：思考状态
     * 状态就尝试定义成题目问的那样，看看状态转移方程是否容易得到。
     * dp[i]：表示前缀子串 s[0:i] 分割成若干个回文子串所需要最小分割次数。
     * 步骤 2：思考状态转移方程
     * 思考的方向是：大问题的最优解怎么由小问题的最优解得到。
     * 即 dp[i] 如何与 dp[i - 1]、dp[i - 2]、...、dp[0] 建立联系。
     * 比较容易想到的是：如果 s[0:i] 本身就是一个回文串，那么不用分割，即 dp[i] = 0 ，这是首先可以判断的，否则就需要去遍历；
     * 接下来枚举可能分割的位置：即如果 s[0:i] 本身不是一个回文串，就尝试分割，枚举分割的边界 j。
     * 如果 s[j + 1, i] 不是回文串，尝试下一个分割边界。
     * 如果 s[j + 1, i] 是回文串，则 dp[i] 就是在 dp[j] 的基础上多一个分割。
     * 于是枚举 j 所有可能的位置，取所有 dp[j] 中最小的再加 1 ，就是 dp[i]。
     * 得到状态转移方程如下：
     * dp[i] = min([dp[j] + 1 for j in range(i) if s[j + 1, i] 是回文])
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii/solution/dong-tai-gui-hua-by-liweiwei1419-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
