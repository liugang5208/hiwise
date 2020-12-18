package com.sky.hiwise.algorithms.leetcode.array;

public class LongestTurbulentSubarray978 {
    /**
     * 978. 最长湍流子数组
     * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
     * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
     * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
     * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
     * 返回 A 的最大湍流子数组的长度。
     * 示例 1：
     * 输入：[9,4,2,10,7,8,8,1,9]
     * 输出：5
     * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
     */
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ans = 1;
        int anchor = 0;
        for (int i = 1; i < n; i++) {
            int c = Integer.compare(arr[i - 1], arr[i]);
            // 1 -1 or -1 1 乘积都为-1
            if (i == n - 1 || c * Integer.compare(arr[i], arr[i + 1]) != -1) {
                //不连续交替时 计算长度 重新定位 anchor
                if (c != 0) {
                    ans = Math.max(ans, i - anchor + 1);
                }
                anchor = i;
            }
        }
        return ans;
    }
    /**
     * 方法：滑动窗口
     * 思路
     * 显然，我们只需要关注相邻两个数字之间的符号就可以了。 如果用 -1, 0, 1 代表比较符的话（分别对应 <、 =、 >），那么我们的目标就是在符号序列中找到一个最长的元素交替子序列 1, -1, 1, -1, ...（从 1 或者 -1 开始都可以）。
     * 这些交替的比较符会形成若干个连续的块 。我们知道何时一个块会结束：当已经到符号序列末尾的时候或者当序列元素不再交替的时候。
     * 举一个例子，假设给定数组为 A = [9,4,2,10,7,8,8,1,9]。那么符号序列就是 [1,1,-1,1,-1,0,-1,1]。它可以被划分成的块为 [1], [1,-1,1,-1], [0], [-1,1]。
     * 算法
     * 从左往右扫描这个数组，如果我们扫描到了一个块的末尾（不再交替或者符号序列已经结束），那么就记录下这个块的答案并将其作为一个候选答案，然后设置下一个元素（如果有的话）为下一个块的开头。
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray/solution/zui-chang-tuan-liu-zi-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
