package com.sky.hiwise.algorithms.leetcode.slidwindow;

public class LongSubsStrAtLeastKRepeatChar395 {
    /**
     * 395. 至少有 K 个重复字符的最长子串
     * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     * 示例 1：
     * 输入：s = "aaabb", k = 3
     * 输出：3
     * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2：
     * 输入：s = "ababbc", k = 2
     * 输出：5
     * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     * 提示：
     * 1 <= s.length <= 104
     * s 仅由小写英文字母组成
     * 1 <= k <= 105
     */
    public int longestSubstring(String s, int k) {
        int ans = 0;
        int n = s.length();
        for (int t = 1; t <= 26; t++) {
            int left = 0, right = 0, total = 0, less = 0;
            int[] cnt = new int[26];
            while (right < n) {
                int rightIdx = s.charAt(right) - 'a';
                cnt[rightIdx]++;
                if (cnt[rightIdx] == 1) {
                    total++;
                    less++;
                }
                if (cnt[rightIdx] == k) {
                    less--;
                }
                while (total > t) {
                    int leftIdx = s.charAt(left) - 'a';
                    cnt[leftIdx]--;
                    if (cnt[leftIdx] == 0) {
                        total--;
                        less--;
                    }
                    if (cnt[leftIdx] == k - 1) {
                        less++;
                    }
                    left++;
                }
                if (less == 0) {
                    ans = Math.max(ans, right - left + 1);
                }
                right++;
            }
        }
        return ans;
    }
}
