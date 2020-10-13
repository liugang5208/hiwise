package com.sky.hiwise.algorithms.leetcode.dynamic;

public class UglyNumber264 {

    /**
     * 264. 丑数 II
     * 编写一个程序，找出第 n 个丑数。
     * 丑数就是只包含质因数 2, 3, 5 的正整数。
     * 示例:
     * 输入: n = 10
     * 输出: 12
     * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     * 说明:
     * 1 是丑数。
     * n 不超过1690。
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int i2 = 0, i3 = 0, i5 = 0;
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[i2] * 2, dp[i3] * 3), dp[i5] * 5);
            if (dp[i2] * 2 == dp[i]) {
                i2 += 1;
            }
            if (dp[i3] * 3 == dp[i]) {
                i3 += 1;
            }
            if (dp[i5] * 5 == dp[i]) {
                i5 += 1;
            }
        }
        return dp[n - 1];
    }


    /**
     * 丑数问题
     * 面试题 17.09. 第 k 个数
     * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。
     * 注意，不是必须有这些素因子，而是必须不包含其他的素因子。
     * 例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
     * 示例 1:
     * 输入: k = 5
     * 输出: 9
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        int[] dp = new int[k];
        dp[0] = 1;
        int i3 = 0, i5 = 0, i7 = 0;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(dp[i3] * 3, Math.min(dp[i5] * 5, dp[i7] * 7));
            if (dp[i3] * 3 == dp[i]) {
                i3++;
            }
            if (dp[i5] * 5 == dp[i]) {
                i5++;
            }
            if (dp[i7] * 7 == dp[i]) {
                i7++;
            }
        }
        return dp[k - 1];
    }

    /**
     * 313. 超级丑数
     * 编写一段程序来查找第 n 个超级丑数。
     * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
     * 示例:
     * 输入: n = 12, primes = [2,7,13,19]
     * 输出: 32
     * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     * 说明:
     * 1 是任何给定 primes 的超级丑数。
     *  给定 primes 中的数字以升序排列。
     * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
     * 第 n 个超级丑数确保在 32 位有符整数范围内。
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int[] index = new int[primes.length];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, dp[index[j]] * primes[j]);
            }
            dp[i] = min;
            for (int j = 0; j < primes.length; j++) {
                if (dp[index[j]] * primes[j] == dp[i]) {
                    index[j] ++;
                }
            }
        }
        return dp[n - 1];
    }

    public static int nthUglyNumber(int n, int a, int b, int c) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        int ia = 0, ib = 0, ic =0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.min(dp[ia] * a, Math.min(dp[ib] * b, dp[ic] * c));
            System.out.println("i:" + i+ "dp[i]" +dp[i] + "ia:" + ia + "ib:" + ib + "ic:"+ic);
            if (dp[ia] * a == dp[i]) {
                ia++;
            }
            if (dp[ib] * b == dp[i]) {
                ib++;
            }
            if (dp[ic] * c == dp[i]) {
                ic++;
            }
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(dp[i]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        nthUglyNumber(5, 2, 11,13);
    }
}
