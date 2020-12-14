package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.Arrays;
import java.util.Comparator;

public class MaxWidthRamp962 {

    /**
     * 962. 最大宽度坡
     * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
     * 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
     * 示例 1：
     * 输入：[6,0,8,2,1,5]
     * 输出：4
     * 解释：
     * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
     * @param A
     * @return
     */
    public int maxWidthRamp(int[] A) {
        int n = A.length;
        Integer[] idxs = new Integer[n];
        for (int i = 0; i < n; i++) {
            idxs[i] = i;
        }
        Arrays.sort(idxs, Comparator.comparingInt(i -> A[i]));
        int m = n;
        int ans = 0;
        for (int i : idxs) {
            ans = Math.max(ans, i - m);
            m = Math.min(i, m);
        }
        return ans;
    }
    /**
     * 方法一：排序
     * 思路与算法
     *
     * 对于每一个形如 A[i] = v 的元素，我们将其索引 i 按照对应值 v 排序之后的顺序写下。例如， A[0] = 7, A[1] = 2, A[2] = 5, A[3] = 4，我们应该这样顺序写下索引值 i=1, i=3, i=2, i=0。
     *
     * 然后，当我们写下一个索引 i 的时候，我们可以得到候选的宽度答案 i - min(indexes_previously_written) （如果这个值是正数的话）。 我们可以用一个变量 m 记录已经写下的最小索引。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/maximum-width-ramp/solution/zui-da-kuan-du-po-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
