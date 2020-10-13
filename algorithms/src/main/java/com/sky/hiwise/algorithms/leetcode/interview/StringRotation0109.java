package com.sky.hiwise.algorithms.leetcode.interview;

public class StringRotation0109 {

    /**
     * 面试题 01.09. 字符串轮转
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
     * 示例1:
     *  输入：s1 = "waterbottle", s2 = "erbottlewat"
     *  输出：True
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        s2 += s2;
        return s2.contains(s1);
    }
}
