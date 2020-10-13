package com.sky.hiwise.design.structure;

import java.io.InputStream;
import java.io.Serializable;
import java.io.Writer;
import java.net.Socket;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class Client {

	/**
	 * @param arg*100
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	Child c = new Child();
		//System.out.println((int)(100*Math.random()));
		LRUCache lru=new LRUCache<String,String>();
		lru.put("liu", "123");
		lru.put("gang","456");
		lru.put("ga","789456");
		System.out.println(lru.get("gang"));
		String str="111111";
		StringBuffer sbf1=new StringBuffer("A");
		StringBuffer sbf2=new StringBuffer("B");
		swap(sbf1,sbf2);
		System.out.println(sbf1+"  "+sbf2);*/
	//List l=new ArrayList<String>();
	//Vector<E> 
	//LinkedList<E>
    //SocketClient
	//ExceptionDemo
		//new Child();
		/*int i=5,j=20;
		do{
			if(++i>=j--){
				continue;}
		}while(i<8);
		System.out.println(i+"========"+j);
		final long mm=24*60*60*1000*1000;
		final long ss=24*60*60*1000;
		System.out.println(mm/ss);
		String str1="hello";
		String str2="he"+new String("llo");
		System.out.println(str1==str2);
	
	Thread aa=new Thread();
	System.err.println(args.length);*/
	//InputStream
	//Writer
	
	//aa.e
		/*int N=5;
		int temp;
		for(int i=1;i<=N;i++){
			for(int j=0;j<N;j++){
				temp=i+j;
				if(temp<=N)
					System.out.print(temp);
				else 
					System.out.print(0);
			}
			for(int j=N-2;j>=0;j--){
				temp=i+j;
				if(temp<=N)
					System.out.print(temp);
				else 
					System.out.print(0);
			}
			System.out.println();
		}*/
	//	Vector<E>


		System.out.println(solve_cube(4,5));
	}

	public static void swap(StringBuffer sbf1,StringBuffer sbf2){
		sbf1.append(sbf2);
		sbf2=sbf1;
	}
	public static int solve_work(int m,int n){
		if(m==0&&n==0)
			return 0;
		if(m==1&&n==0)
			return 1;
		if(m==0&&n==1)
			return 1;
		if(m==0)
			return solve_work(m,n-1);
		if(n==0)
			return solve_work(m-1,n);
		return solve_work(m,n-1)+solve_work(m-1,n);
	}
	public static int solve_cube(int m,int n){
		int q[][]=new int[m+1][n+1];
		q[0][0]=0;
		for(int i=1;i<=n;i++){
			q[0][i]=1;
		}
		for(int j=1;j<=m;j++){
			q[j][0]=1;
		}
		for(int i=1;i<=m;i++){
			for(int j=1;j<=n;j++){
				q[i][j]=q[i-1][j]+q[i][j-1];
			}
		}
		int res=q[m][n];
		return res;
	}
}
