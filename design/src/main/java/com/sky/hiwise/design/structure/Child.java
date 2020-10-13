package com.sky.hiwise.design.structure;

public class Child extends Parent {
	//static String childName=print("child");
	public Child(){
		System.out.println("child  constructor!");
	}
	{
		System.out.println("child block!");
	}
	static {
		System.out.println("child static block!");
	}

	 /*private int val = 1;
	 public void output()
	 {
	  System.out.println("A.output(),val="+val);
	 }
	 public Child(int val)
	 {
	  super();
	  this.val = val;
	  System.out.println("A constructor");
	 }
	 public Child(String name){
		 super(name);
		// this.name=name;
		System.out.println("Child constructor with string name");
		 
	 }*/
	
	/*private String name="child";
	
	public Child(){
		tellName();
		tellTheName(name);
		
	}
	
	public void tellName(){
		System.out.println("Child tell name :"+name);
	}
	
	public void tellTheName(String name){
		System.out.println("Child tell the name :"+name);
	}*/
	
/*	static {
		System.out.println("b");
	}
	public Child(){
		System.out.println("y");
	}*/
	 
}
