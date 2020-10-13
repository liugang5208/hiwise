package com.sky.hiwise.algorithms.leetcode.string;

public class MaxCommonStrings1071 {

    /**
     * 1071. 字符串的最大公因子
     * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
     * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
     * 示例 1：
     * 输入：str1 = "ABCABC", str2 = "ABC"
     * 输出："ABC"
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        return str2.substring(0, gcd(str2.length(), str1.length()));
    }

    public int gcd (int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}
