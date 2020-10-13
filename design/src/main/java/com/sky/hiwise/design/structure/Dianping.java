package com.sky.hiwise.design.structure;

import java.util.ArrayList;
import java.util.List;

public class Dianping {

	/**
	 * @param args
	 */
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
     /*new Child();
     Parent.print("lllll");*/
//	Child c = new Child(5);
  //   }

/*	public int chazhao(int n){
		String a[]={"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		List S=new ArrayList<String>();
		for(int i=0;i<a.length;i++){
			
		}
		
	}*/
	
	/**  
	 * 求最大公约数  
	 *   
	 * 欧几里德算法又称辗转相除法，用于计算两个整数a，b的最大公约数。  
	 * 其计算原理依赖于下面的定理：gcd(a,b) = gcd(b,a mod b)   
	 *   
	 * @author heartraid  
	 */  
	    public static int getDivisor(int a,int b){   
	        if(a%b==0) return b;   
	        if(b%a==0) return a;   
	        return a>=b?getDivisor(b,a%b):getDivisor(a,b%a);   
	    }   
	       
	    public static void main(String[] args) {   
	        System.out.println(Dianping.getDivisor(3,72));   
	    }   
	  
	}

