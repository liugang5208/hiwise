package com.sky.hiwise.algorithms.leetcode.sort;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {4, 46,61,21,7,8,3,2,90,2,25,56,2,111,134,2,6,7,3,5,255};
        sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    public static void quickSort(int[] nums, int left, int right) {
        if( left >= right ) {
            return;
        }

        if( right - left <= 15 ){
            // 对于小规模数组, 使用插入排序
        }
        int p = partiton2Ways(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    private static int partiton(int[] nums, int left, int right) {
        //近乎有序的数组排序优化 完全有序退化成 O(N2) 随机选择标定元素
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap( nums, left , (int)(Math.random()*(right - left + 1)) + left );

        int v = nums[left];
        int j = left;  // arr[l+1...j] < v ; arr[j+1...i) > v
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < v) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, left, j);
        return j;
    }

    //处理大量重复元素情况
    private static int partiton2Ways(int[] nums, int left, int right) {
        swap( nums, left , (int)(Math.random()*(right - left + 1)) + left );
        int v = nums[left];
        int i = left + 1;
        int j = right;
        while (true) {
            while (i <= right && nums[i] < v) {
                i++;
            }
            while (j > left + 1 && nums[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(nums, j, i);
            i++;
            j--;
        }
        swap(nums, left, j);
        return j;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
