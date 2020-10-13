package com.sky.hiwise.algorithms.leetcode.dynamic;

public class LongestPalindromicSubstring5 {

    /**
     * 5. 最长回文子串
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0 ) {
            return s;
        }
        int start = 0, end = 0;
        for(int i = 0; i < s.length() - 1; i ++) {
            int s1 = palindrome(s, i, i);
            int s2 = palindrome(s, i, i+1);
            int len = Math.max(s1, s2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
       return right - (left + 1);
    }

    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0 ) {
            return s;
        }
        String res = s.substring(0, 1);
        for(int i = 0; i < s.length() - 1; i ++) {
            String s1 = palindrome2(s, i, i);
            String s2 = palindrome2(s, i, i+1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    public String palindrome2(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println((new LongestPalindromicSubstring5()).longestPalindrome(s));
    }

    /**
     * 动态规划解法
     * dp[i][j]表示s[i...j]是否是回文子串
     * dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
     * （考虑下标 j-1-(i+1)+1 < 2=> j-i<3 => j-i+1<4 s[i...j]长度为2和3时不用检查子串是否是回文）
     * dp[i][i] = true;
     * 在得到一个值为true时记录最长起始位置和长度，填表完后再截取
     * @param s
     * @return
     */
    public String longestPalindromeDP(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int len = s.length();
        int maxLen = 1;
        int begin = 0;

        boolean[][] dp = new boolean[len][len];
        for (int right = 1; right < len; right++) {
            for (int left = 0; left < right; left++) {
//                if (s.charAt(left) != s.charAt(right)) {
//                    dp[left][right] = false;
//                } else {
//                    if (right - left < 3) {
//                        dp[left][right] = true;
//                    } else {
//                        dp[left][right] = dp[left+1][right-1];
//                    }
//                }
                if (s.charAt(left) == s.charAt(right) && (right - left < 3 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
                if (dp[left][right] && right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    begin = left;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
