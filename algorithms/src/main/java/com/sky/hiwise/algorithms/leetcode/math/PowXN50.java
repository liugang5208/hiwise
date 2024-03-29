package com.sky.hiwise.algorithms.leetcode.math;

public class PowXN50 {

    /**
     * 50. Pow(x, n)
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * 示例 1:
     *
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * 示例 2:
     *
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * 示例 3:
     *
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     * 说明:
     *
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     * @param x
     * @param N
     * @return
     */
    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    /**
     * 根据递归计算的结果，如果 n 为偶数，那么 x^n = y^2 * x
     *  ；如果 n 为奇数，那么 x^n = y^2 * x
     * 递归的边界为 n = 0,任意数的 0 次方均为 1。
     * 由于每次递归都会使得指数减少一半，因此递归的层数为O(logn)，算法可以在很快的时间内得到结果。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
