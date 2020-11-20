package com.sky.hiwise.algorithms.leetcode.greedy;

public class MinOperationsMakeArrayEqual1551 {
    /**
     * 1551. 使数组中所有元素相等的最小操作数
     * 存在一个长度为 n 的数组 arr ，其中 arr[i] = (2 * i) + 1 （ 0 <= i < n ）。
     * 一次操作中，你可以选出两个下标，记作 x 和 y （ 0 <= x, y < n ）并使 arr[x] 减去 1 、arr[y] 加上 1 （即 arr[x] -=1 且 arr[y] += 1 ）。最终的目标是使数组中的所有元素都 相等 。题目测试用例将会 保证 ：在执行若干步操作后，数组中的所有元素最终可以全部相等。
     * 给你一个整数 n，即数组的长度。请你返回使数组 arr 中所有元素相等所需的 最小操作数 。
     * 示例 1：
     * 输入：n = 3
     * 输出：2
     * 解释：arr = [1, 3, 5]
     * 第一次操作选出 x = 2 和 y = 0，使数组变为 [2, 3, 4]
     * 第二次操作继续选出 x = 2 和 y = 0，数组将会变成 [3, 3, 3]
     * 示例 2：
     * 输入：n = 6
     * 输出：9
     */
    public int minOperations(int n) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = 2 * i + 1;
            if (x > n) {
                ans += (x - n);
            }
        }
        return ans;
    }
    /**
     * 方法一：贪心
     * 思路及算法
     * 注意到每次我们进行操作时都同时进行了「加」操作和「减」操作，这样我们只需要记录「减」操作的数量即可知道我们操作了多少次。
     * 对于每一个大于 nn 的数，其与 nn 的差值即为该元素需要进行的「减」操作的数量。我们只要统计所有大于 nn 的数与 nn 的差值，就能计算出我们操作了多少次。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-operations-to-make-array-equal/solution/shi-shu-zu-zhong-suo-you-yuan-su-xiang-deng-de-z-4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
