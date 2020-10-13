package com.sky.hiwise.algorithms.leetcode.math;

public class BaseSeven504 {

    /**
     * 504. 七进制数
     * 给定一个整数，将其转化为7进制，并以字符串形式输出。
     *
     * 示例 1:
     *
     * 输入: 100
     * 输出: "202"
     * 示例 2:
     *
     * 输入: -7
     * 输出: "-10"
     * 注意: 输入范围是 [-1e7, 1e7] 。
     */
    public String convertToBase7(int num) {
        if (num < 0) {
            return "-" + convertToBase7(-1 * num);
        }
        if (num < 7) {
            return String.valueOf(num);
        }
        return convertToBase7(num / 7) + String.valueOf(num % 7);
    }
}
