package com.sky.hiwise.algorithms.leetcode.slidwindow;

public class SubArraysWithKDiffIntegers992 {
    /**
     * 992. K 个不同整数的子数组
     * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组。
     * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
     * 返回 A 中好子数组的数目。
     * 示例 1：
     * 输入：A = [1,2,1,2,3], K = 2
     * 输出：7
     * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
     * 示例 2：
     *
     * 输入：A = [1,2,1,3,4], K = 3
     * 输出：3
     * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
     * 提示：
     * 1 <= A.length <= 20000
     * 1 <= A[i] <= A.length
     * 1 <= K <= A.length
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }
    /**
     * 把原问题转换成为容易求解的问题
     * 友情提示：这里把 「恰好」 转换成为 「最多」须要一点求解「双指针（滑动窗口）」问题的经验。建立在熟练掌握这一类问题求解思路的基础上。
     * 把「恰好」改成「最多」就可以使用双指针一前一后交替向右的方法完成，这是因为 对于每一个确定的左边界，最多包含 KK 种不同整数的右边界是唯一确定的，并且在左边界向右移动的过程中，右边界或者在原来的地方，或者在原来地方的右边。
     * 而「最多存在 KK 个不同整数的子区间的个数」与「恰好存在 K 个不同整数的子区间的个数」的差恰好等于「最多存在 K - 1K−1 个不同整数的子区间的个数」。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers/solution/k-ge-bu-tong-zheng-shu-de-zi-shu-zu-by-l-ud34/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    /**
     * 最多包含 K 个不同整数的子区间的个数
     * @param A
     * @param K
     * @return
     */
    private int atMostKDistinct(int[] A, int K) {
        int n = A.length, left = 0, right = 0, ans = 0, count = 0;
        int[] freq = new int[n + 1]; //1 <= A[i] <= A.length
        while (right < n) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right ++;
            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            //此时区间为[left, right)
            ans += right - left;
        }
        return ans;
    }
}
