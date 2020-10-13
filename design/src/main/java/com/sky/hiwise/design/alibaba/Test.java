package com.sky.hiwise.design.alibaba;

public class Test {
	public static void main(String[] args) {
		System.out.println(testt());
	}

	public static int testt(){
		int x=99;
		try {
		return x;	
		} finally{
			x=8;
		}
	}
}
