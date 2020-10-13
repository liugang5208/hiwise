package com.sky.hiwise.design.template;

public abstract class Template {
	
	public void templateMethod(){
		doOperation1();
		
		doOperation2();
		
		doOperation3();
	}

	public abstract void doOperation1();
	
	public abstract void doOperation2();
	
	public  void doOperation3(){
		System.out.println("父类中的具体操作3");
		
	}
	
}
