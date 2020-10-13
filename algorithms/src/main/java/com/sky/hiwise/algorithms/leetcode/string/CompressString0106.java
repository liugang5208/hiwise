package com.sky.hiwise.algorithms.leetcode.string;

public class CompressString0106 {

    /**
     * 面试题 01.06. 字符串压缩
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
     * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
     * 你可以假设字符串中只包含大小写英文字母（a至z）。
     * 示例1:
     *  输入："aabcccccaaa"
     *  输出："a2b1c5a3"
     * 示例2:
     *  输入："abbccd"
     *  输出："abbccd"
     *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
     * 提示：
     * 字符串长度在[0, 50000]范围内。
     * 通过次数6,271提交次数12,510
     * @param S
     * @return
     */
    public String compressString(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        int len = S.length();
        String ans = "";
        char temp = S.charAt(0);
        int cnt = 1;
        for (int i = 1; i < len; i++) {
            if (temp == S.charAt(i)) {
                cnt++;
            } else {
                ans += String.valueOf(temp) + cnt;
                cnt = 1;
                temp = S.charAt(i);
            }
        }
        ans += String.valueOf(temp) + cnt;
        return ans.length() >= len ? S : ans;
    }
}
