package com.sky.hiwise.algorithms.leetcode.string;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class FindSubStr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findSubStr();
	}
	
	public static void findSubStr() {
		String str1   = "abcdefghik";
		String substr = "abcij";
		char[] char1  = str1.toCharArray();
		char[] subchar = substr.toCharArray();
		System.out.println(str1.indexOf(substr));
	}

}
