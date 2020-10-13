package com.sky.hiwise.algorithms.leetcode.sort;

import java.util.*;

public class CountSmallerNumbersAfterSelf315 {

    /**
     * 315. 计算右侧小于当前元素的个数
     * 给定一个整数数组 nums，按要求返回一个新数组 counts。
     * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     * 示例:
     * 输入: [5,2,6,1]
     * 输出: [2,1,1,0]
     * 解释:
     * 5 的右侧有 2 个更小的元素 (2 和 1).
     * 2 的右侧仅有 1 个更小的元素 (1).
     * 6 的右侧有 1 个更小的元素 (1).
     * 1 的右侧有 0 个更小的元素.
     * @param nums
     * @return
     */
    private int[] indexs;
    private int[] counter;
    private int[] tempIndex;
    public List<Integer> countSmaller(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        List<Integer> ans = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return ans;
        }
        indexs = new int[len];
        counter = new int[len];
        tempIndex = new int[len];
        for (int i = 0; i < len; i++) {
            indexs[i] = i;
        }
        mergeSort(nums, 0, len - 1);

        for(int i = 0; i < len; i++) {
            ans.add(counter[i]);
        }
        return ans;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        if (nums[indexs[mid]] > nums[indexs[mid + 1]]) {
            merge(nums, left, mid, right);
        }
    }

    private void merge(int[] nums, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            tempIndex[i] = indexs[i];
        }
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                indexs[k] = tempIndex[j];
                j++;
            } else if (j > right) {
                indexs[k] = tempIndex[i];
                i++;
                counter[indexs[k]] += (right - mid);
            } else if (nums[tempIndex[i]] <= nums[tempIndex[j]]) {
                indexs[k] = tempIndex[i];
                i++;
                counter[indexs[k]] += (j - (mid + 1));
            } else {
                indexs[k] = tempIndex[j];
                j++;
            }
        }
    }
}
