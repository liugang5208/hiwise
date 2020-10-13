package com.sky.hiwise.algorithms.leetcode.dynamic;

public class DecodeWays91 {

    /**
     * 91. 解码方法
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     * 示例 1:
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
     * 示例 2:
     * 输入: "226"
     * 输出: 3
     * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     * @param s
     * @return
     */
    int memo[];
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        memo = new int[s.length() + 1];
        return numDecode(s, 0);
    }

    public int numDecode(String s, int start) {
        //递归的第一步，应该是加终止条件，避免死循环。
        if (s.length() == start) {
            return 1;
        }
        //以0位开始的数是不存在的
        if (s.charAt(start) == '0') {
            return 0;
        }
        if (memo[start] > 0) {
         return memo[start];
        }
        //递归的递推式应该是如果index的后两位小于等于26，
        // numDecode(s, start) = numDecode(s, start+1)+numDecode(s, start+2)
        // 否则numDecode(s, start) = numDecode(s, start+1)
        int ans1 = numDecode(s, start + 1);
        int ans2 = 0;
        if (start < s.length() - 1) {
            int ten = (s.charAt(start) - '0') * 10;
            int one = (s.charAt(start + 1) - '0');
            if (ten + one <= 26) {
                ans2 = numDecode(s, start + 2);
            }
        }
        memo[start] = ans1 + ans2;
        return memo[start];
    }

    public int numDecodingsDp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        dp[len - 1] = s.charAt(len - 1) == '0' ? 0 : 1;
        for(int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }
            if ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println((new DecodeWays91()).numDecodingsDp("00"));
    }

}
