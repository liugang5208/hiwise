package com.sky.hiwise.algorithms.leetcode.string;

public class ValidPalindrome125 {

    /**
     * 125. 验证回文串
     给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     说明：本题中，我们将空字符串定义为有效的回文串。
     示例 1:  输入: "A man, a plan, a canal: Panama"  输出: true
     示例 2:  输入: "race a car"  输出: false
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        String s1 = s.toLowerCase();

        char [] chars = s1.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (!Character.isLetterOrDigit(chars[start])) {
                start ++;
            } else if (!Character.isLetterOrDigit(chars[end])) {
                end --;
            } else if (chars[start] != chars[end]) {
                return false;
            } else {
                start ++;
                end --;
            }
        }
        return true;
    }
}
