package com.sky.hiwise.algorithms.leetcode.string;

public class KMP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pos = kmpIndex("abababacbab", "abac",0);
		System.out.println(pos);
	}
	
	/*
	返回子串Ptn在主串Tag的第pos个字符后(含第pos个位置)第一次出现的位置，若不存在，则返回-1
	采用KMP算法，这里的位置全部以从0开始计算为准，其中T非空，0<=pos<=Tlen
	*/
	public static int kmpIndex(String str, String subStr, int pos) {
	    int i = pos;  //主串当前正待比较的位置，初始为pos
	    int j = 0;   //子串当前正待比较的位置，初始为0
	    int Tlen = str.length();  //主串长度
	    int Plen = subStr.length();  //子串长度
	    int[] next = new int[Plen];
	    int[] next1 = getNextVal(str,next,Plen);
	    while(i<Tlen && j<Plen) {
	        if(j==-1 || str.charAt(i) == subStr.charAt(j)) {   //如果当前字符相同，或者在子串的第一个字符处失配，则继续向下比较
	            i++;
	            j++;
	        } else {
	        	//如果当前字符不同，则i保持不变，j变为下一个开始比较的位置
	            //next数组是KMP算法的关键，i不回退，
	            //而是继续与子串中的nex[j]位置的字符进行比较
	            j = next1[j];
	        }
	    }
	    
	    if(j >= Plen)
	        return i - Plen;
	    else
	        return -1;
	}
	
	/*
	求next数组的改进数组中各元素的值，保存在长为len的nextval数组中
	*/
	public static int[] getNextVal(String str, int[] nextval, int len) {
	    int j = 0;
	    int k = -1;
	    nextval[0] = -1;
	    while(j<len-1) {
	        if(k == -1 || str.charAt(j) == str.charAt(k)) {   
	        	//如果满足上面分析的Pk = Pj的情况，则继续比较下一个字符，
	            //并得next[j+1]=next[j]+1
	            j++;
	            k++;
	            if(str.charAt(j) != str.charAt(k))
	                nextval[j] = k;
	            else  //Ptn[j]与Ptn[k]相等时，直接跳到nextval[k]
	                nextval[j] = nextval[k];
	        } else {   //如果符合上面分析的第2种情况，则依据next[k]继续寻找下一个比较的位置
	            k = nextval[k];
	        }
	    }
	    
	    return nextval;
	}

}
