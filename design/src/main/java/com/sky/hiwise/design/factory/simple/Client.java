package com.sky.hiwise.design.factory.simple;

//import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

public class Client {

	/**
	 * @param args
	 */
	private static Product prodA,prodB;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    prodA=SimpleFactory.createProduct(1);
		prodA.create();
		//prodA=SimpleFactory.createProduct(3);
		prodB=SimpleFactory.createProduct(2);
		prodB.create();
	}

}
