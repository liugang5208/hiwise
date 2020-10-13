package com.sky.hiwise.algorithms.leetcode.sort;

public class QuickSort3Ways {

    public static void sort(int[] nums) {
        threeWaysSort(nums, 0, nums.length - 1);
    }

    public static void threeWaysSort(int[] nums, int left, int right) {
        if( left >= right ) {
            return;
        }

        if( right - left <= 15 ){
            // 对于小规模数组, 使用插入排序
        }
        swap( nums, left , (int)(Math.random()*(right - left + 1)) + left );
        int v = nums[left];
        int lt = left;
        int gt = right + 1;
        int i = left + 1;
        while (i < gt) {
            if (nums[i] < v) {
                swap(nums, lt + 1, i);
                lt++;
                i++;
            } else if (nums[i] > v) {
                swap(nums, gt - 1, i);
                gt--;
            } else {
                i++;
            }
        }
        swap(nums, left, lt);
        threeWaysSort(nums, left, lt - 1);
        threeWaysSort(nums, gt, right);
    }


    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 46,61,21,7,8,3,2,90,2,25,56,2,111,134,2,6,7,3,5,255};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
