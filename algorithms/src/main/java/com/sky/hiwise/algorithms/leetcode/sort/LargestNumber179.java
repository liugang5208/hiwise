package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.Arrays;

public class LargestNumber179 {

    /**
     * 179. 最大数
     * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
     * 示例 1:
     * 输入: [10,2]
     * 输出: 210
     * 示例 2:
     * 输入: [3,30,34,5,9]
     * 输出: 9534330
     * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        String[] numStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numStrs, (a, b) -> {
            String s1 = a + b;
            String s2 = b + a;
            return s2.compareTo(s1);
        });
        if (numStrs[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numStrs.length; i++) {
            sb.append(numStrs[i]);
        }

        return sb.toString();
    }
}
