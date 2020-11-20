package com.sky.hiwise.algorithms.leetcode.sort;

public class RelativeSortArray1122 {
    /**
     * 1122. 数组的相对排序
     * 给你两个数组，arr1 和 arr2，
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     * 示例：
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(x, upper);
        }
        int[] freq = new int[upper + 1];
        for (int x : arr1) {
            freq[x]++;
        }
        int[] ans = new int[arr1.length];
        int idx = 0;
        for (int y : arr2) {
            for (int i = 0; i < freq[y]; i++) {
                ans[idx++] = y;
            }
            freq[y] = 0;
        }
        for (int i = 0; i <= upper; i++) {
           for (int j = 0; j < freq[i]; j++) {
               ans[idx++] = i;
           }
        }
        return ans;
    }
}
