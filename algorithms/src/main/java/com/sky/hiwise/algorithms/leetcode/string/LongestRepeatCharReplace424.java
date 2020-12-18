package com.sky.hiwise.algorithms.leetcode.string;

public class LongestRepeatCharReplace424 {

    /**
     * 424. 替换后的最长重复字符
     * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
     * 在执行上述操作后，找到包含重复字母的最长子串的长度。
     * 注意:
     * 字符串长度 和 k 不会超过 104。
     * 示例 1:
     * 输入:
     * s = "ABAB", k = 2
     * 输出:
     * 4
     * 解释:
     * 用两个'A'替换为两个'B',反之亦然。
     */
    public int characterReplacement(String s, int k) {
        int[] cntChar = new int[26];
        int left = 0, right = 0, ans = 0, maxCount = 0;
        while (right < s.length()) {
            int idx = s.charAt(right) - 'A';
            cntChar[idx] ++;
            maxCount = Math.max(maxCount, cntChar[idx]);  //当前窗口内的最多字符的个数
            if (right - left + 1 - maxCount > k) {  //需要替换的字符个数就是当前窗口的大小减去窗口中数量最多的字符的数量
                cntChar[s.charAt(left) - 'A']--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

}
