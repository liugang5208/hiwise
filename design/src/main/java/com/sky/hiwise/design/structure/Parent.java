package com.sky.hiwise.design.structure;

public class Parent {
	
	//static String parentName=print("parent");
	public Parent(){
		System.out.println("parent constructor!");
	}
	{
		System.out.println("parent block!");
	}

	static {
		System.out.println("parent static block!");
	}
	
	
	static void aa(){
		System.out.println("child static block!");
	}
	
	/*private int val = 10;
	protected String name;
	 public void output()
	 {
	  System.out.println("P.output(),val+"+val);
	 }
	 public Parent()
	 {
	  System.out.println("P constructor");
	  output();
	 }
	 public Parent(String name){
		 this.name=name;
		 System.out.println("P constructor with name ");
	 }
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}*/
	
	/*private String name="base";
	
	public Parent(){
		tellName();
		tellTheName(name);
		
	}
	
	public void tellName(){
		System.out.println("parent tell name :"+name);
	}
	
	public void tellTheName(String name){
		System.out.println("parent tell the name :"+name);
	}*/
	
	/*static {
		System.out.println("a");
	}
	public Parent(){
		System.out.println("x");
	}*/
	
}
