package com.sky.hiwise.design.factory.abstra;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Creator create1=new ConcreteCreate1();
		ProductA prodA1= create1.factoryA();
		prodA1.create();
		
		ProductB prodB1=create1.factoryB();
		prodB1.create();
		
		Creator create2=new ConcreteCreate2();
		ProductA prodA2= create2.factoryA();
		prodA2.create();
		
		ProductB prodB2=create2.factoryB();
		prodB2.create();
		
		
		
		
		

	}

}
