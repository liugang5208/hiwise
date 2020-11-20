package com.sky.hiwise.algorithms.leetcode.dynamic;

public class NumSubArraysOddSum1524 {
    /**
     * 1524. 和为奇数的子数组数目
     * 给你一个整数数组 arr 。请你返回和为 奇数 的子数组数目。
     * 由于答案可能会很大，请你将结果对 10^9 + 7 取余后返回。
     * 示例 1：
     * 输入：arr = [1,3,5]
     * 输出：4
     * 解释：所有的子数组为 [[1],[1,3],[1,3,5],[3],[3,5],[5]] 。
     * 所有子数组的和为 [1,4,9,3,8,5].
     * 奇数和包括 [1,9,3,5] ，所以答案为 4 。
     * 示例 2 ：
     * 输入：arr = [2,4,6]
     * 输出：0
     * 解释：所有子数组为 [[2],[2,4],[2,4,6],[4],[4,6],[6]] 。
     * 所有子数组和为 [2,6,12,4,10,6] 。
     * 所有子数组和都是偶数，所以答案为 0 。
     * 示例 3：
     */
    public int numOfSubarrays(int[] arr) {
        final int MODULO = 1000000007;
        //odd 奇数  even 偶数
        int odd = 0, even = 1;
        int sum = 0;
        int subarrays = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            //
            subarrays = (subarrays + (sum % 2 == 0 ? odd : even)) % MODULO;
            if (sum % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return subarrays;
    }
    /**
     * 方法一：前缀和 + 数学
     * 这道题要求返回和为奇数的子数组数目。为了快速计算任意子数组的和，可以通过维护前缀和的方式。
     * 这道题只需要知道每个子数组的和的奇偶性，不需要知道子数组的和的具体值，因此不需要维护每一个前缀和，只需要维护奇数前缀和的数量与偶数前缀和的数量。
     * 分别使用 odd和even 表示奇数前缀和的数量与偶数前缀和的数量。初始时，\textit{odd}=0odd=0，\textit{even}=1even=1，因为空的前缀的和是 00，也是偶数前缀和。
     * 遍历数组 \textit{arr}arr 并计算前缀和。对于下标 ii 的位置的前缀和（即 \textit{arr}[0]+\textit{arr}[1]+\ldots+\textit{arr}[i]arr[0]+arr[1]+…+arr[i]），根据奇偶性进行如下操作：
     * 当下标 ii 的位置的前缀和是偶数时，如果下标 jj 满足 j < ij<i 且下标 jj 的位置的前缀和是奇数，则从下标 j+1j+1 到下标 ii 的子数组的和是奇数，因此，以下标 ii 结尾的子数组中，和为奇数的子数组的数量即为奇数前缀和的数量 \textit{odd}odd；
     * 当下标 ii 的位置的前缀和是奇数时，如果下标 jj 满足 j < ij<i 且下标 jj 的位置的前缀和是偶数，则从下标 j+1j+1 到下标 ii 的子数组的和是奇数，因此，以下标 ii 结尾的子数组中，和为奇数的子数组的数量即为偶数前缀和的数量 \textit{even}even。
     * 上述下标 jj 的最小可能取值为 -1−1，当 j=-1j=−1 时表示下标 jj 的位置的前缀为空。
     * 在更新和为奇数的子数组数量之后，需要根据下标 ii 的位置的前缀和的奇偶性更新 \textit{odd}odd 或 \textit{even}even 的值。如果前缀和是奇数，则 \textit{odd}odd 的值加 11；如果前缀和是偶数，则 \textit{even}even 的值加 11。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/number-of-sub-arrays-with-odd-sum/solution/he-wei-qi-shu-de-zi-shu-zu-shu-mu-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
