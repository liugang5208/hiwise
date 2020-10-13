package com.sky.hiwise.algorithms.leetcode.string;

public class LongestComSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	String str1 = "AGGTAB";
		String str2 = "GXTXAYB";
		int len = lcs(str1, str2);
		System.out.println(len);*/
		String str ="ababaabababababa";
		//int len1 = lps1(str, 0, str.length() - 1);
		int len1 = lpsDp(str);
		System.out.println(len1);
	}
	
	public static int lcs(String str1, String str2) {
		if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty()) {
        	return 0;
        }
		int len1 = str1.length();
		int len2 = str2.length();
		
		int[][] lcs = new int[len1 + 1][len2 + 1];
	    int i, j;
	    
	    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
	       that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
	    for (i = 0; i <= len1; i++) {
		    for (j = 0; j <= len2; j++) {
				if (i == 0 || j == 0) {
					lcs[i][j] = 0;
				} else if (str1.charAt(i -1) == str2.charAt(j - 1)) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
		    }
	    }
	    
	    /* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
	    return lcs[len1][len2];
	}
	
	/**
	 * 给一个字符串，找出它的最长的回文子序列的长度。例如，如果给定的序列是“BBABCBCAB”，则输出应该是7，“BABCBAB”是在它的最长回文子序列。
	 *  “BBBBB”和“BBCBB”也都是该字符串的回文子序列，但不是最长的。注意和最长回文子串的区别(参考：最长回文串)！
	 * 这里说的子序列，类似最长公共子序列LCS( Longest Common Subsequence)问题，可以是不连续的。这就是LPS(Longest Palindromic Subsequence)问题。
	 * 最直接的解决方法是：生成给定字符串的所有子序列，并找出最长的回文序列，这个方法的复杂度是指数级的。下面来分析怎么用动态规划解决。
	 * @param str
	 * @param i
	 * @param j
	 * @return
	 */
	public static int lps1(String str, int i, int j) {
		 //一个元素即为1
		 if (i == j) {
			 return 1;
		 }
		    
		 if(i > j) {
			 return 0; //因为只计算序列 seq[i ... j]
		 }
   
		 // 如果首尾相同
		 if (str.charAt(i) == str.charAt(j)) {
			 return lps1(str, i + 1, j - 1) + 2;
		 }
      
		 // 首尾不同
		 return Math.max(lps1(str, i, j - 1), lps1(str, i + 1, j) );
	}
	
	public static int lpsDp(String str) {
		int len = str.length(),temp;
		
		int dp[][] = new int[len][len];
		for(int i = 0; i < len; i++) {
			dp[i][i] = 1;
		}
		
		// i 表示 当前长度为 i+1的 子序列
		for(int i = 1; i < len; i++){
			temp = 0;
	        //考虑所有连续的长度为i+1的子串. 该串为 str[j, j+i]
	        for(int j = 0; j + i < len; j++) {
	        	
	        	//如果首尾相同
	            if(str.charAt(j) == str.charAt(j + i)){
	            	temp = dp[j + 1][j + i - 1] + 2;
	            }else{
	            	temp = Math.max(dp[j + 1][j +i ], dp[j][j + i - 1]);
	            }
	            dp[j][j + i] = temp;
        	}
        }
		
		//返回串 str[0][n-1] 的结果
	    return dp[0][len - 1];
	}
}
