package com.sky.hiwise.algorithms.leetcode.greedy;

public class LongestPalindrome409 {

    /**
     * 409. 最长回文串
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     * 注意:
     * 假设字符串的长度不会超过 1010。
     * 示例 1:
     * 输入:
     * "abccccdd"
     * 输出:
     * 7
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[128];
        for(char c : s.toCharArray()) {
            count[c] ++;
        }
        int ans = 0;
        for(int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans ++;
            }
        }
        return ans;
    }
    /**
     * 方法一： 贪心 【通过】
     * 思路
     * 回文串是一个正读和反读都一样的字符串。对一个左边的字符 i 右边一定会有一个对称 i。比如 'abcba'， 'aa'，'bb' 这几个回文串。其中第一个有点特殊，中间的 c 是唯一的。
     * 算法
     * 对于每个字母，假设它出现了 v 次。我们可以让 v // 2 * 2 个字母左右对称。例如，对于字符串 'aaaaa'，其中 'aaaa' 是左右对称，其长度为 5 // 2 * 2 = 4。
     * 最后，如果有任何一个满足 v % 2 == 1 的 v，那么这个字符就可能是回文串中唯一的那个中心。针对这种情况，我们需要判断 v % 2 == 1 && ans % 2 == 0，后面的判断主要是为了防止重复计算。
     */
}
