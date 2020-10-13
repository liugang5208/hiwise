package com.sky.hiwise.algorithms.leetcode.string;

public class LongestSubString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String str = longestSubstring("hello world","cpp hello java");
         System.out.println(str);

	}
	
	/**
	 * 最长公共子序列 & 最长公共子串的区别：
	 * 找两个字符串的最长公共子串，这个子串要求在原字符串中是连续的。而最长公共子序列则并不要求连续。
	 * 最长公共子序列的问题参考：最长公共子序列。这两个都可以使用动态规划解决，但是思路不太一样。
	 * 我们采用一个二维矩阵来记录中间的结果。这个二维矩阵怎么构造呢？直接举个例子吧：”bab”和”caba”(当然我们现在一眼就可以看出来最长公共子串是”ba”或”ab”)
	 * 我们看矩阵的斜对角线最长的那个就能找出最长公共子串。不过在二维矩阵上找最长的由1组成的斜对角线也是件麻烦费时的事，
	 * 下面改进：当要在矩阵是填1时让它等于其左上角元素加1。
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String longestSubstring(String str1, String str2) {
		        StringBuilder sb = new StringBuilder();
		        if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty()) {
		        	return "";
		        }
		            
		        int[][] num = new int[str1.length()][str2.length()];
		        int maxlen  = 0; //记录最长字串的长度
		        int lastSubsBegin = 0; //记录最长子串开始的位置
		        for (int i = 0; i < str1.length(); i++) {
		            for (int j = 0; j < str2.length(); j++) {
		                if (str1.charAt(i) == str2.charAt(j)) {
		                    if ((i == 0) || (j == 0))
		                        num[i][j] = 1;
		                    else
		                        num[i][j] = 1 + num[i - 1][j - 1];
		                    if (num[i][j] > maxlen) {
		                        maxlen = num[i][j];
		                        
		                        //当前最长的子串，在str1中开始的位置
		                        int thisSubsBegin = i - num[i][j] + 1;
		                        
		                        //如果是同一个子串
		                        if (lastSubsBegin == thisSubsBegin) {
		                            sb.append(str1.charAt(i));
		                        } else {
		                        	
		                            //不是话的重新生成一个新的子串
		                            lastSubsBegin = thisSubsBegin;
		                            sb = new StringBuilder();
		                            sb.append(str1.substring(lastSubsBegin, i + 1));
		                        }
		                    }
		                }
		            }
	            }
		        
		        return sb.toString();
		    }
}
