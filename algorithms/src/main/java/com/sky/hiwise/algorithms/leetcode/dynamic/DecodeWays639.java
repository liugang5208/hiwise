package com.sky.hiwise.algorithms.leetcode.dynamic;

public class DecodeWays639 {

    /**
     * 639. 解码方法 II
     * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 要 解码 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，"11106" 可以映射为：
     * "AAJF" 对应分组 (1 1 10 6)
     * "KJF" 对应分组 (11 10 6)
     * 注意，像 (1 11 06) 这样的分组是无效的，因为 "06" 不可以映射为 'F' ，因为 "6" 与 "06" 不同。
     * 除了 上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符，可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）。例如，编码字符串 "1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条消息。对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息。
     * 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目 。
     * 由于答案数目可能非常大，返回对 109 + 7 取余 的结果。
     * 示例 1：
     * 输入：s = "*"
     * 输出：9
     * 解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
     * 可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
     * 因此，"*" 总共有 9 种解码方法。
     */
    public static final int MOD = 1000000007;
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        long[] dp = new long[len + 1];
        dp[0] = 1;
        // a = f[i-2], b = f[i-1], c = f[i]
        long a = 0, b = 1, c = 0;
        for (int i = 1; i <= len; i++) {
            // c = b * check1digit(s.charAt(i - 1)) % MOD;
            // if (i > 1) {
            //     c = (c + a * check2digit(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            // }
            // a = b;
            // b = c;
            dp[i] = check1digit(s.charAt(i - 1)) * dp[i - 1] % MOD;
            if (i > 1) {
                dp[i] = (dp[i] + dp[i - 2] * check2digit(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            }
        }
        return (int)dp[len];
    }

    public int check2digit(char ch1, char ch2) {
        if (ch1 == '*' && ch2 == '*') {
            /**
             * 如果 s[i-1]和s[i] 均为 *，那么它们对应着 [11,19]以及 [21, 26]中的任意一种编码，有 15 种方案。因此状态转移方程为: fi = 15 & fi-2
             */
            return 15;
        }
        if (ch1 == '*') {
            //如果仅有 s[i−1] 为 *，那么当s[i]∈[0,6] 时，s[i−1] 可以选择 1 和 2；当s[i]∈[7,9] 时，s[i−1] 只能选择 1。因此状态转移方程为：
            return ch2 <= '6' ? 2 : 1;
        }
        if (ch2 == '*') {
           if (ch1 == '1') {
               return 9;
           }
           if (ch1 == '2') {
               return 6;
           }
           return 0;
        }
        return ch1 != '0' && (ch1 - '0') * 10 + (ch2 - '0') <= 26 ? 1 : 0;


    }

    public int check1digit(char ch) {
        return ch == '*' ? 9 : 1;
    }
}
