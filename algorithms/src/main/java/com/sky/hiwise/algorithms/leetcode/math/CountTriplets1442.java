package com.sky.hiwise.algorithms.leetcode.math;

public class CountTriplets1442 {
    /**
     * 1442. 形成两个异或相等数组的三元组数目
     * 给你一个整数数组 arr 。
     * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
     * a 和 b 定义如下：
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 注意：^ 表示 按位异或 操作。
     * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     * 示例 1：
     * 输入：arr = [2,3,1,6,7]
     * 输出：4
     * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
     * 示例 2：
     * 输入：arr = [1,1,1,1,1]
     * 输出：10
     */
    public int countTriplets(int[] arr) {
        int count = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int ans = arr[i];
            for (int k = i + 1; k < n; k++) {
                ans ^= arr[k];
                if (ans == 0) {
                    count += k - i;
                }
            }
        }
        return count;
    }
    /**
     * 解题思路
     * 因为a 和 b 定义如下：
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
     * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 所以有arr[i] ^ arr[i + 1] ^ ... ^ arr[k] = a ^ b = 0;
     * 所以就遍历数组，从下标i开始向后遍历异或，当异或结果为0时，表示满足题目要求的三元组出现，
     * 此时区间为[i,k]，区间内三元组的个数为 k - i（因为区间内的任意一个j，都和i，k组成满足题目的一个三元组）。
     * 作者：wei-xiao-mian-dui-w
     * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/solution/javashuang-100jie-fa-by-wei-xiao-mian-du-ltle/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
