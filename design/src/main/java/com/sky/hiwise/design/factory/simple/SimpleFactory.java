package com.sky.hiwise.design.factory.simple;

public class SimpleFactory {
	
	public static Product createProduct(int prodId){
		
		switch (prodId){
		case 1:
			return new ConcretePorductA();
		case 2:
			return new ConcretePorductB();
		default:
			throw new RuntimeException("there is no this product,please check!");
			
		}
	}
	

}
