package com.sky.hiwise.algorithms.leetcode.string;

import java.util.HashMap;
import java.util.Iterator;

public class FindIncludeStr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		findStrHash();
	}
	
	public static void findStrHash() {
		String str1 = "abcdk";
		String str2 = "abcdefghijk";
		
		int num = 0;
		char[] a = str1.toCharArray();
		char[] b = str2.toCharArray();
		HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
		for(int i = 0 ; i < str1.length(); i ++ ) {
			
			if (!hash.containsKey(a[i])) {
				hash.put(a[i], 1);
				num ++ ;
			}
		}
		
		for(int j = 0 ; j < str2.length(); j ++ ) {
			
			if (hash.containsKey(b[j]) && hash.get(b[j]) == 1) {
				hash.put(b[j], 0);
				num -- ;
			}
			
			if (num == 0) {
				break;
			}
		}
		
		if (num == 0) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}

}
