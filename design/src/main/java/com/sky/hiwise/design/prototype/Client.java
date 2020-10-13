package com.sky.hiwise.design.prototype;

import java.util.ArrayList;
import java.util.List;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SampleClass sam=new SampleClass(999);
		Prototype proSam=sam.clone();
		System.out.println("sam == proSam ?"+(sam==proSam));
		System.out.println("sam : "+sam.toString());
		System.out.println("proSam : "+proSam.toString());
		
		
		ComplexClass comp=new ComplexClass();
		comp.setString("一个字符串");
		List list=new 	ArrayList();	
		list.add("字串");
		list.add(34);
		comp.setList(list);
		Prototype proComp=comp.clone();
		System.out.println("comp == proComp ?"+(comp==proComp));
		System.out.println("comp : "+comp.toString());
		System.out.println("proComp : "+proComp.toString());
		

	}

}
