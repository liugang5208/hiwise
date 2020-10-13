package com.sky.hiwise.design.structure;

public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		bubbleSort();
	}

	public static void bubbleSort() {
		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62,
				99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51 };
		int temp = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = a.length - 1; j >i; j--) {
				if (a[j-1] > a[j ]) {
					temp = a[j-1];
					a[j-1] = a[j];
					a[j ] = temp;
				}
			}
		}
	/*	for (int i=0;i<a.length-1;i++){
			for(int j=0;j<a.length-1-i;j++){
				if(a[j]>a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}*/
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}
