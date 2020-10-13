package com.sky.hiwise.design.factory.method;

public class ConcreteCreatorA implements Creator {

	@Override
	public Product createProduct() {
		// TODO Auto-generated method stub
		return new ConcretePorductA();
	}

}
