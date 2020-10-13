package com.sky.hiwise.algorithms.leetcode.string;

import java.util.ArrayList;

public class FindSameNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//两个排序数组找出相同的元素
		//
		FindSameNumMerge();
		findNumByBinSearch();
	}
	
	public static void findNumByBinSearch() {
		int[] array1 = {1,5,8,9,15,21,27,28,30,32,35,42,55,61,70,78};
		int[] array2 = {1,4,6,7,8,9,10,15,21,27,28,30,32,35,42,55,61,70,78,92};
		ArrayList<Integer>  array3 = new ArrayList<Integer>();
		int index1 = 0, index2 = 0;
		int[] result = new int[3];
		int i = 0;
		while (index1 < array1.length && index2 < array2.length) {
			i++;
			result = BinSearch(array2, array1[index1], index2, array2.length - 1);//拿A中的一个元素去B数组中查找  
			index2 = result[1];
			if (result[0] == 1) {
				array3.add(array2[index2]);
			}
			index1 ++;
		}
		
		System.out.println(array3 + "count :" + i*result[2]);
	}
	
	
	public static int[] BinSearch(int arr[], int num, int low, int high) {
		int middle = 0;
		int[] result = new int[3];
		int i = 0;
		while (low <= high) {
			i++;
			middle = (low + high) / 2;
			if (num >= arr[middle]) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		
		if (high > -1 && arr[high] == num) {
			result[0] = 1;
			result[1] = low;
			result[2] = i;
			return result;
		} else {
			result[0] = 0;
			result[1] = low;
			result[2] = i;
			return result;
		}
	}
	
	//两个有序非重数组，如果查找数组之间的重复元素
	public static void FindSameNumMerge() {
		int[] array1 = {1,5,8,9,15,21,27,28,30,32,35,42,55,61,70,78};
		int[] array2 = {1,4,6,7,8,9,10,15,21,27,28,30,32,35,42,55,61,70,78,92};
		ArrayList<Integer>  array3 = new ArrayList<Integer>();
		int index1 = 0, index2 = 0;
		int i =0;
		while (index1 < array1.length && index2 < array2.length) {
			i++;
			if (array1[index1] < array2[index2]) {
				index1 ++;
			} else if (array1[index1] > array2[index2]) {
				index2 ++;
			} else {
				array3.add(array1[index1]);
				index1 ++; index2 ++;
			}
		}
		System.out.println(array3+"count :" + i);
	}
	
	
}
