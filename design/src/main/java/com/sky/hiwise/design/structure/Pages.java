package com.sky.hiwise.design.structure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pages {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		  int[] page = {1,2,3,4,5,1,2,5,1,2,3,4,5} ;
		  List<Integer> m = new ArrayList<Integer>() ;
		  int count = 0 ;
		  for(int p : page){
		   if(m.size()<3){
		    if(m.indexOf(p)==-1){
		     m.add(p) ;
		     count++ ;
		    }
		   }
		   else{
		    if(m.indexOf(p)==-1){
		     m.remove(0) ;
		     m.add(p) ;
		     count++ ;
		    }
		   }
		   System.out.println(m);
		  }
		  System.out.println("总共缺页次数："+count+"次");
		 }

}
