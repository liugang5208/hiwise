package com.sky.hiwise.algorithms.leetcode.sort;

public class SortArrayByParity922 {
    /**
     * 922. 按奇偶排序数组 II
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     * 你可以返回任何满足上述条件的数组作为答案。
     * 示例：
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     */
    public int[] sortArrayByParityII(int[] A) {
        int len = A.length;
        int right = 1;
        for (int left = 0; left < len; left += 2) {
            if (A[left] % 2 == 1) {
                while (A[right] % 2 == 1) {
                    right += 2;
                }
                swap(A, left, right);
            }
        }
        return A;
    }

    private void swap(int[] A, int x, int y) {
        int temp = A[x];
        A[x] = A[y];
        A[y] = temp;
    }
    /**
     * 方法二： 双指针
     * 思路与算法
     * 如果原数组可以修改，则可以使用就地算法求解。
     * 为数组的偶数下标部分和奇数下标部分分别维护指针 i, ji,j。随后，在每一步中，如果 A[i]A[i] 为奇数，则不断地向前移动 jj（每次移动两个单位），直到遇见下一个偶数。此时，可以直接将 A[i]A[i] 与 A[j]A[j] 交换。我们不断进行这样的过程，最终能够将所有的整数放在正确的位置上。
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii/solution/an-qi-ou-pai-xu-shu-zu-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

}
