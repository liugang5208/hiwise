package com.sky.hiwise.design.structure;

import java.util.Random;

public class LCS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getLCS();
	}
	
	public static void getLCS(){
		int subLen1=6;
		int subLen2=6;
		//String a=getRandomStrings(subLen1);
		//String b=getRandomStrings(subLen2);
		String a="234567";
		String b="267895";
		Long startTime=System.nanoTime();
		int[][] opt=new int[subLen1+1][subLen2+1];
		
		for(int i=subLen1-1;i>=0;i--){
			for(int j=subLen2-1;j>=0;j--){
				if(a.charAt(i)==b.charAt(j))
					opt[i][j]=opt[i+1][j+1]+1;
				else
					opt[i][j]=Math.min(opt[i+1][j], opt[i][j+1]);
			}
		}
		System.out.println("String1:"+a);
		System.out.println("String2:"+b);
		System.out.println("LCS:");
		int i=0,j=0;
		while(i<subLen1&&j<subLen2){
			if(a.charAt(i)==b.charAt(j)){
				System.out.println(a.charAt(i));
				i++;
				j++;
			}else if(opt[i+1][j]>=opt[i][j+1])
				i++;
			else
				j++;
		}
		Long endTime=System.nanoTime();
		System.out.println("Total time is "+(endTime-startTime)+ "ns");
	}
	
	public static String getRandomStrings(int length){
		StringBuffer buffer=new StringBuffer("abcdefghijklmopqrstuvwxyz");
		StringBuffer sb=new StringBuffer();
		Random r=new Random();
		int range=buffer.length();
		for(int i=0;i<length;i++){
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
}
