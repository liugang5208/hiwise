package com.sky.hiwise.algorithms.leetcode.search;

public class ArrangingCoins441 {
    /**
     * 441. 排列硬币
     * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
     * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
     * 示例 1：
     * 输入：n = 5
     * 输出：2
     * 解释：因为第三行不完整，所以返回 2 。
     * 示例 2：
     * 输入：n = 8
     * 输出：3
     * 解释：因为第四行不完整，所以返回 3 。
     */
    public int arrangeCoins(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - (left - 1)) / 2;
            if ((long)mid * (mid + 1) <= (long)2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    /**
     * 方法一：二分查找
     * 思路和算法
     * 根据等差数列求和公式可知，前 kk 个完整阶梯行所需的硬币数量为
      total=2k×(k+1)/2
     * 因此，可以通过二分查找计算 n 枚硬币可形成的完整阶梯行的总行数。
     * 因为1≤n≤2的31次方−1，所以 n 枚硬币至少可以组成 1 个完整阶梯行，至多可以组成 n 个完整阶梯行（在 n=1 时得到）。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/arranging-coins/solution/pai-lie-ying-bi-by-leetcode-solution-w52c/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
