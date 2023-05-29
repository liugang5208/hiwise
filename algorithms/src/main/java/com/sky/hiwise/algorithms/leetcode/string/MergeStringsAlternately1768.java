package com.sky.hiwise.algorithms.leetcode.string;

/**
 * @date: 2022-10-24 16:24
 **/
public class MergeStringsAlternately1768 {

    /**
     * 1768. 交替合并字符串
     * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
     * 返回 合并后的字符串 。
     * 示例 1：
     * 输入：word1 = "abc", word2 = "pqr"
     * 输出："apbqcr"
     * 解释：字符串合并情况如下所示：
     * word1：  a   b   c
     * word2：    p   q   r
     * 合并后：  a p b q c r
     * @param word1
     * @param word2
     * @return
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0, n = word1.length(), m = word2.length();

        while (i < n || j < m) {
            if (i < n) {
                sb.append(word1.charAt(i));
                i++;
            }
            if (j < m) {
                sb.append(word2.charAt(j));
                j++;
            }
        }
        return sb.toString();
    }
}
