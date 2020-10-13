package com.sky.hiwise.algorithms.leetcode.string;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int arr[] = {10, 7, 8, 9, 1, 5};
		 int n = arr.length;
		 quickSort(arr, 0, n-1);
		 for (int i : arr) {
			 System.out.println(i);
		}
	}
	
	/* arr[] --> 待排序数组, l  --> 开始位置, h  -->结束位置 */
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int p = partition(arr, low, high); /* p为划分的中间位置 */
			quickSort(arr, low, p - 1);
			quickSort(arr, p + 1, high);
		}
	}
	
	public static int partition(int[] arr, int low, int high) {
		int value = arr[high];
		int i = low - 1;
		for (int j = low; j <= high - 1; j++) {
			if (arr[j] <= value) {
				i ++;
				arr = swap(arr, i, j);
			}
		}
		arr = swap(arr, i + 1, high);
		return (i + 1);//返回基准元素的最终位置
	}
	
	public static int[] swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i]   = arr[j];
		arr[j]   = temp;
		return arr;
	}
}
