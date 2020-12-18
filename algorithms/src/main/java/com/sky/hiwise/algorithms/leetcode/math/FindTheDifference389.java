package com.sky.hiwise.algorithms.leetcode.math;

public class FindTheDifference389 {
    /**
     * 389. 找不同
     * 给定两个字符串 s 和 t，它们只包含小写字母。
     * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
     * 请找出在 t 中被添加的字母。
     * 示例 1：
     * 输入：s = "abcd", t = "abcde"
     * 输出："e"
     * 解释：'e' 是那个被添加的字母。
     * 示例 2：
     * 输入：s = "", t = "y"
     * 输出："y"
     */
    public char findTheDifference(String s, String t) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnt[c - 'a']--;
            if (cnt[c - 'a'] < 0) {
                return c;
            }
        }
        return ' ';
    }
    /**
     * 方法一：计数
     * 首先遍历字符串 ss，对其中的每个字符都将计数值加 11；然后遍历字符串 tt，对其中的每个字符都将计数值减 11。
     * 当发现某个字符计数值为负数时，说明该字符在字符串 tt 中出现的次数大于在字符串 ss 中出现的次数，因此该字符为被添加的字符。
     * 方法二：求和
     * 将字符串 ss 中每个字符的 ASCII 码的值求和，得到 A_sA；对字符串 tt 同样的方法得到 A_tA。两者的差值 A_t-A_sA即代表了被添加的字符。
     * 方法三：位运算
     * 如果将两个字符串拼接成一个字符串，则问题转换成求字符串中出现奇数次的字符。
     * 类似于「136. 只出现一次的数字」，我们使用位运算的技巧解决本题。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-difference/solution/zhao-bu-tong-by-leetcode-solution-mtqf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public char findTheDifference1(String s, String t) {
        char ans = 0;
        for (char c : s.toCharArray()) {
            ans ^= c;
        }
        for (char c : t.toCharArray()) {
            ans ^= c;
        }
        return (char)ans;
    }
}
