package com.sky.hiwise.algorithms.leetcode.math;

public class SqrtX69 {

    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x < 2) return x;
        int start = 2, end = x / 2;
        long sqrt = 0;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            sqrt = (long)mid * mid;
            if (sqrt > x) {
                end = mid - 1;
            } else if (sqrt < x) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return end;
    }
}
