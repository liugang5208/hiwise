package com.sky.hiwise.algorithms.leetcode.string;

public class SortUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {8,9,6,3,21,52,56,18,57,49,25,56,58,69,1,25,3};
		String str="asdfghjkpoi";
		String aa = (new StringBuffer(str)).reverse().toString();
		System.out.println(aa);
		//mergeSort(arr, 0, 16);
		//bubbleSort(arr);
		//selectSort(arr);
		/*insertSort(arr);
		for (int i : arr) {
			 System.out.println(i);
		}*/
		//basketSort(arr);
	}
	
	/**
	 * 有一个已经有序的数据序列，要求在这个已经排好的数据序列中插入一个数，但要求插入后此数据序列仍然有序，这个时候就要用到一种新的排序方法——插入排序法,
	 * 插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据，算法适用于少量数据的排序，时间复杂度为O(n^2),是稳定的排序方法。
	 * 插入算法把要排序的数组分成两部分：第一部分包含了这个数组的所有元素，但将最后一个元素除外（让数组多一个空间才有插入的位置），而第二部分就只包含这一个元素（即待插入元素）。
	 * 在第一部分排序完成后，再将这个最后元素插入到已排好序的第一部分中。
	 * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
	 * ⒈ 从第一个元素开始，该元素可以认为已经被排序
	 * ⒉ 取出下一个元素，在已经排序的元素序列中从后向前扫描
	 * ⒊ 如果该元素（已排序）大于新元素，将该元素移到下一位置
	 * ⒋ 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
	 * ⒌ 将新元素插入到下一位置中
	 * ⒍ 重复步骤2~5
	 * 如果比较操作的代价比交换操作大的话，可以采用二分查找法来减少比较操作的数目。该算法可以认为是插入排序的一个变种，称为二分查找排序。
	 * @param arr
	 */
	public static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for(int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					swap(arr, j, j -1);
				}
			}
		}
	}
	
	/**
	 * 冒泡排序算法的运作如下：（从后往前）比较相邻的元素。如果第一个比第二个大，就交换他们两个。
	 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
	 * 针对所有的元素重复以上的步骤，除了最后一个。
	 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
	 * @param arr
	 */
	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					arr = swap(arr, i, j);
				}
			}
		}
	}
	
	/**
	 * 选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
	 * 存放在序列的起始位置，直到全部待排序的数据元素排完。 
	 * 选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
	 * 选择排序法的第一层循环从起始元素开始选到倒数第二个元素，主要是在每次进入的第二层循环之前，将外层循环的下标赋值给临时变量，
	 * 接下来的第二层循环中，如果发现有比这个最小位置处的元素更小的元素，则将那个更小的元素的下标赋给临时变量，
	 * 最后，在二层循环退出后，如果临时变量改变，则说明，有比当前外层循环位置更小的元素，需要将这两个元素交换.
	 * @param arr
	 */
	public static void selectSort(int[] arr) {
		int minIndex = 0;
		for (int i = 0; i < arr.length - 1; i++) {
			minIndex = i;    //无序区的最小数据数组下标
			for (int j = i + 1; j < arr.length; j ++) {
				//在无序区中找到最小数据并保存其数组下标
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			
			//如果不是无序区的最小值位置不是默认的第一个数据，则交换之。
			if (minIndex != i) {
				arr = swap(arr, i, minIndex);
			}
		}
	}
	
	/**
	 * 最快最简单的排序—桶排序
	 * 先找出待排序数组中的最大数以确定桶的个数
	 * @param arr
	 */
	public static void basketSort(int[] arr) {
		int n   = arr.length;
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = (max > arr[i]) ? max : arr[i];
		}
		
		int[] basket = new int[max + 1];
		for (int i = 0; i < n; i++) {
			basket[arr[i]] ++;
		}
		
		for (int j = max; j >= 0; j--) {
			for (int k = 0; k < basket[j]; k++) {
				System.out.println(j);
			}
		}
	}
	
	/**
	 * 归并排序是一个分治算法。归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，
	 * 即把待排序序列分为若干个有序的子序列，再把有序的子序列合并为整体有序序列。
	 * merg() 函数是用来合并两个已有序的数组.  是整个算法的关键
	 * 时间复杂度：O(nlogn) 空间复杂度：O(n)    稳定排序
	 * @param arr
	 * @param low
	 * @param middle
	 * @param high
	 */
	public static void merge(int[] arr, int low, int middle, int high) {
		int i, j, k;
		int len1 = middle - low + 1;
		int len2 = high - middle;
		int[] left  = new int[len1];
		int[] right = new int[len2];
		
		for (i = 0; i < len1; i++ ) {
			left[i] = arr[low + i];
		}
		
		for (j = 0; j < len2; j++ ) {
			right[j] = arr[middle + 1 + j];
		}
		
		i = 0; j = 0; k = low;
		
		while (i < len1 && j < len2) {
			if (left[i] <= right[j]) {
				arr[k] = left[i];
	            i++;
			} else {
				arr[k] = right[j];
	            j++;
			}
			k ++;
		}
		
		/* 复制剩下的部分 left */
		while (i < len1) {
			arr[k] = left[i];
	        i ++;
	        k ++;
		}
		
		/* 复制剩下的部分 right */
		while (j < len2) {
			arr[k] = right[j];
	        j ++;
	        k ++;
		}
	}
	
	/**
	 * 归并排序
	 * @param arr
	 * @param low
	 * @param high
	 */
	public static void mergeSort(int[] arr, int low, int high) {
		if (low < high) {
			int middle = low + (high - low)/2; //和 (low + high)/2 一样, 但是可以避免溢出在 l 和 r较大时
			mergeSort(arr, low, middle);
	        mergeSort(arr, middle + 1, high);
	        merge(arr, low, middle, high);
		}
	}

	/**
	 * 快速排序
	 * @param arr 待排序数组
	 * @param low 开始位置
	 * @param high 结束位置
	 */
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int p = partition(arr, low, high); /* p为划分的中间位置 */
			quickSort(arr, low, p - 1);
			quickSort(arr, p + 1, high);
		}
	}
	
	/**
	 * 查找基准元素
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
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
	
	/**
	 * 交换数组中的值
	 * @param arr
	 * @param i
	 * @param j
	 * @return
	 */
	public static int[] swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i]   = arr[j];
		arr[j]   = temp;
		return arr;
	}
}
