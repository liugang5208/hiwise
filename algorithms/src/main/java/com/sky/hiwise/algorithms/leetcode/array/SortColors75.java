package com.sky.hiwise.algorithms.leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortColors75 {

    public static void main(String[] args) {
        int[] arr = new int[]{12,1,3,4,5,3,3,2323,4,5,5243,78};
        int[] aux = Arrays.copyOfRange(arr, 4, 9);
        System.out.println(aux.length);
//        SortColors75 sc = new SortColors75();
//        int []nums = {1, 2, 0, 1, 0, 1};
//        sc.sortColors(nums);
//        ArrayUtil.print(nums);

    }

    /**
     * 三路快排
     计数排序 常用于元素值为有限个的情况

     * 75. 分类颜色
     给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     注意:
     不能使用代码库中的排序函数来解决这道题。
     示例:
     输入: [2,0,2,1,1,0]
     输出: [0,0,1,1,2,2]
     进阶：
     ● 一个直观的解决方案是使用计数排序的两趟扫描算法。
     首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
     ● 你能想出一个仅使用常数空间的一趟扫描算法吗？
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int zero = -1;
        int two = nums.length;
        for(int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                ArrayUtil.swap(nums, i, --two);
            } else {
                ArrayUtil.swap(nums, ++zero, i++);
            }
        }
    }

    /**
     * 88. 合并两个有序数组
     给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     说明:
     ● 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     ● 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     示例:
     输入:
     nums1 = [1,2,3,0,0,0], m = 3
     nums2 = [2,5,6],       n = 3
     输出: [1,2,2,3,5,6]
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n -1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            nums1[len--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }

        if (m == -1) {
            while(n >= 0) {
                nums1[len--] = nums2[n--];
            }
        }
    }

    /**
     * 215. 数组中的第K个最大元素
     在未排序的数组中找到第 k 个最大的元素。请注意，它是数组有序排列后的第 k 个最大元素，而不是第 k 个不同元素。
     例如，给出 [3,2,1,5,6,4] 和 k = 2，返回 5。
     注意事项:  你可以假设 k 总是有效的，1 ≤ k ≤ 数组的长度。
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k - 1, 0, nums.length - 1);
    }

    private int quickSelect(int[] arr, int k, int left, int right){
        int pivot = arr[(left + right) / 2];
        int orgL = left, orgR = right;
        while(left <= right){
            // 从右向左找到第一个小于枢纽值的数
            while(arr[left] > pivot){
                left ++;
            }
            // 从左向右找到第一个大于枢纽值的数
            while(arr[right] < pivot){
                right --;
            }
            // 将两个数互换
            if(left <= right){
                ArrayUtil.swap(arr, left, right);
                left ++;
                right --;
            }
        }
        // 最后退出的情况应该是右指针在左指针左边一格
        // 这时如果右指针还大于等于k，说明kth在左半边
        if(orgL < right && k <= right) return quickSelect(arr, k, orgL, right);
        // 这时如果左指针还小于等于k，说明kth在右半边
        if(left < orgR && k >= left) return quickSelect(arr, k, left, orgR);
        return arr[k];
    }

    public static int findKthLargest2(int[] nums, int k) {
        int[] heap = new int[k];
        //首先取k个元素
        System.arraycopy(nums, 0, heap, 0, k);
        //从倒数k个节点开始 调整数组成为最小堆
        for (int i = k / 2 - 1; i >= 0; i--) {
            adjest(heap, i);
        }
        //如果元素小于最小堆 跳过。
        //如果元素大于最小堆 把元素放在堆顶 然后调整堆
        for (int i = k; i < nums.length; i++) {
            if (heap[0] < nums[i]) {
                heap[0] = nums[i];
                adjest(heap, 0);
            }
        }
        //返回堆顶堆元素
        return heap[0];
    }


    /**
     * 调整最小堆
     * @param heap 堆
     * @param i 从哪个 index 开始
     */
    private static void adjest(int[] heap, int i) {
        int temp = heap[i];
        int length = heap.length;
        for (int k = i * 2 + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && heap[k + 1] < heap[k]) {
                k++;
            }
            if (temp <= heap[k]) {
                break;
            } else {
                heap[i] = heap[k];
                i = k;
            }
        }
        heap[i] = temp;
    }

}
