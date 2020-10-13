package com.sky.hiwise.algorithms.leetcode.math;

public class StrobogrammaticNumber248 {
    /**
     * [LeetCode] 248. Strobogrammatic Number III 对称数之三
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
     * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
     * Example:
     * Input: low = "50", high = "100"
     * Output: 3
     * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
     * Note:
     * Because the range might be a large number, the lowand high numbers are represented as string.
     */

    /**
     * 这道题是之前那两道 Strobogrammatic Number II 和 Strobogrammatic Number 的拓展，又增加了难度，
     * 让找给定范围内的对称数的个数，我们当然不能一个一个的判断是不是对称数，也不能直接每个长度调用第二道中的方法，保存所有的对称数，然后再统计个数，这样 OJ 会提示内存超过允许的范围，
     * 所以这里的解法是基于第二道的基础上，不保存所有的结果，而是在递归中直接计数，根据之前的分析，
     * 需要初始化 n=0 和 n=1 的情况，然后在其基础上进行递归，递归的长度 len 从 low 到 high 之间遍历，
     * 然后看当前单词长度有没有达到 len，如果达到了，首先要去掉开头是0的多位数，然后去掉长度和 low 相同但小于 low 的数，
     * 和长度和 high 相同但大于 high 的数，然后结果自增1，然后分别给当前单词左右加上那五对对称数，继续递归调用，参见代码如下
     */
    /**
     * class Solution {
     * public:
     *     int strobogrammaticInRange(string low, string high) {
     *         int res = 0;
     *         for (int i = low.size(); i <= high.size(); ++i) {
     *             find(low, high, "", i, res);
     *             find(low, high, "0", i, res);
     *             find(low, high, "1", i, res);
     *             find(low, high, "8", i, res);
     *         }
     *         return res;
     *     }
     *     void find(string low, string high, string path, int len, int &res) {
     *         if (path.size() >= len) {
     *             if (path.size() != len || (len != 1 && path[0] == '0')) return;
     *             if ((len == low.size() && path.compare(low) < 0) || (len == high.size() && path.compare(high) > 0)) {
     *                 return;
     *             }
     *             ++res;
     *         }
     *         find(low, high, "0" + path + "0", len, res);
     *         find(low, high, "1" + path + "1", len, res);
     *         find(low, high, "6" + path + "9", len, res);
     *         find(low, high, "8" + path + "8", len, res);
     *         find(low, high, "9" + path + "6", len, res);
     *     }
     * };
     */
}
