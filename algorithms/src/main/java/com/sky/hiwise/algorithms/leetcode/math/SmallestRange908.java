package com.sky.hiwise.algorithms.leetcode.math;

import java.util.Arrays;

public class SmallestRange908 {

    /**
     * 908. 最小差值 I
     * 给你一个整数数组 A，请你给数组中的每个元素 A[i] 都加上一个任意数字 x （-K <= x <= K），从而得到一个新数组 B 。
     * 返回数组 B 的最大值和最小值之间可能存在的最小差值。
     * 示例 1：
     * 输入：A = [1], K = 0
     * 输出：0
     * 解释：B = [1]
     * 示例 2：
     * 输入：A = [0,10], K = 2
     * 输出：6
     * 解释：B = [2,8]
     * @param A
     * @param K
     * @return
     */
    public int smallestRangeI(int[] A, int K) {
        int max = A[0], min = A[0];
        for (int x : A) {
            max = Math.max(max, x);
            min = Math.min(min, x);
        }
        return Math.max(0, max - min - 2 * K);
    }

    /**
     * 910. 最小差值 II
     * 给定一个整数数组 A，对于每个整数 A[i]，我们可以选择 x = -K 或是 x = K，并将 x 加到 A[i] 中。
     * 在此过程之后，我们得到一些数组 B。
     * 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
     * 示例 1：
     * 输入：A = [1], K = 0
     * 输出：0
     * 解释：B = [1]
     * 示例 2：
     * 输入：A = [0,10], K = 2
     * 输出：6
     * 解释：B = [2,8]
     */
    public int smallestRangeII(int[] A, int K) {
        int N = A.length;
        Arrays.sort(A);
        int ans = A[N - 1] - A[0];
        for (int i = 0; i < N - 1; i++) {
            int a = A[i], b = A[i + 1];
            int high = Math.max(A[N - 1] - K, a + K);
            int low = Math.min(A[0] + K, b - K);
            ans = Math.min(ans, high - low);
        }
        return ans;
    }
    /**
     * 如 最小差值 I 问题的解决方法，较小的 A[i] 将增加，较大的 A[i] 将变小。
     * 算法
     * 我们可以对上述想法形式化表述：如果 A[i] < A[j]，我们不必考虑当 A[i] 增大时 A[j] 会减小。
     * 这是因为区间 (A[i] + K, A[j] - K) 是 (A[i] - K, A[j] + K) 的子集（这里，当 a > b 时 (a, b) 表示 (b, a) ）。
     * 这意味着对于 (up, down) 的选择一定不会差于 (down, up)。我们可以证明其中一个区间是另一个的子集，通过证明 A[i] + K 和 A[j] - K 是在 A[i] - K 和 A[j] + K 之间。
     * 对于有序的 A，设 A[i] 是最大的需要增长的 i，那么 A[0] + K, A[i] + K, A[i+1] - K, A[A.length - 1] - K 就是计算结果的唯一值。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/smallest-range-ii/solution/zui-xiao-chai-zhi-ii-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
