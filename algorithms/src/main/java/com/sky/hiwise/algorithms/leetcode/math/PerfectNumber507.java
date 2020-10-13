package com.sky.hiwise.algorithms.leetcode.math;

public class PerfectNumber507 {

    /**
     * 507. 完美数
     * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
     * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
     * 示例：
     * 输入: 28
     * 输出: True
     * 解释: 28 = 1 + 2 + 4 + 7 + 14
     * @param num
     * @return
     */
    public boolean checkPerfectNumber(int num) {
        if (num <= 0 ) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        return sum == 2 * num;
    }
}
