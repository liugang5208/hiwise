package com.sky.hiwise.design.factory.method;

public class Client {

	/**
	 * @param args
	 */
	
	private static Creator creatorA,creatorB;
	private static Product prodA,prodB;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		creatorA=new ConcreteCreatorA();
		prodA=creatorA.createProduct();
		prodA.create();
		creatorB=new ConcreteCreatorB();
		prodB=creatorB.createProduct();
		prodB.create();
		
	}

}
