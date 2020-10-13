package com.sky.hiwise.algorithms.leetcode.string;

public class ShortestPalindrome214 {

    /**
     * 214. 最短回文串
     * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
     * 示例 1:
     * 输入: "aacecaaa"
     * 输出: "aaacecaaa"
     * 示例 2:
     * 输入: "abcd"
     * 输出: "dcbabcd"
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        String reverse = sb.reverse().toString();

        int len = s.length();
        for(int i = 0; i < len; i++) {
            if (s.substring(0, len - i).equals(reverse.substring(i))) {
                return reverse.substring(0, i) + s;
            }
        }
        return "";
    }
    /**
     * 直觉
     * 根据问题，我们只能在字符串的开头插入字符。因此，我们可以从字符串开头找到最大的回文子串，然后反转剩余的子串并附加到开头。这必然是正确的答案，因为不可能通过在开头插入字符来得到更短的回文。
     * 举例而言: 字符串 \text{“abcbabcab”}“abcbabcab”。从开头找到的最大回文子串是 \text{“abcba”}“abcba”，剩下的部分是 \text{“bcab”}“bcab”。
     * 因此要求的字符串是 \text{“bcab”}“bcab” 的反转（= \text{“bacb”}“bacb”）+ 原字符串（ = \text{“abcbabcab”}“abcbabcab”）= \text{“bacbabcbabcab”}“bacbabcbabcab”。
     * 算法
     * 创建原字符串 ss 的反转，记为 \text{rev}rev。这将用于从字符串开头找到最大的回文子串。
     * 从 0 到 \text{size(s)}-1size(s)−1 遍历变量i:
     * 若 s[0:n-i] == rev[i:]s[0:n−i]==rev[i:] (亦即：ss 从 00 到 n-in−i 的子串等于 \text{rev}rev 从 ii 到结尾的子串 g)，则从 00 到 n-in−i 的子串为回文子串。
     * 我们首先寻找更大的回文子串。这样，一旦得到最大的回文子串，就可以返回结果。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/shortest-palindrome/solution/zui-duan-hui-wen-chuan-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */


    public static String reverseCharArray(String s) {
        char[] array = s.toCharArray();
        StringBuilder reverse = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--) {
            reverse.append(array[i]);
        }
        return reverse.toString();
    }

}
