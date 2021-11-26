package com.sky.hiwise.algorithms.leetcode.string;

public class BuddyStrings859 {
    /**
     * 859. 亲密字符串
     * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
     * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
     * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
     * 示例 1：
     * 输入：s = "ab", goal = "ba"
     * 输出：true
     * 解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
     * 示例 2：
     * 输入：s = "ab", goal = "ab"
     * 输出：false
     * 解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
     */
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int[] cnt = new int[26];
        int first = -1, second = -1;
        if (s.equals(goal)) {
            for (char c : s.toCharArray()) {
                if (cnt[c - 'a'] > 0) {
                    return true;
                }
                cnt[c - 'a']++;
            }
            return false;
        } else {
            for(int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (first == -1) {
                        first = i;
                    } else if (second == -1) {
                        second = i;
                    } else {
                        return false;
                    }
                }
            }
        }

        return second != -1 && s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first);
    }
}
