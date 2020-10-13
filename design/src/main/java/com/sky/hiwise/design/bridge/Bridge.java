package com.sky.hiwise.design.bridge;

public class Bridge {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
          Abstraction abs=new RefinedAbatraction();
          abs.impl=new ConcreteImplementA();
          abs.operation();
          abs.impl=new ConcreteImplementB();
          abs.operation();
	}

}



