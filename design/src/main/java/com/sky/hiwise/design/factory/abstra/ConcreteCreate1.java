package com.sky.hiwise.design.factory.abstra;

public class ConcreteCreate1 implements Creator {

	@Override
	public ProductA factoryA() {
		// TODO Auto-generated method stub
		return new ConcretePorductA1();
	}

	@Override
	public ProductB factoryB() {
		// TODO Auto-generated method stub
		return new ConcretePorductB1();
	}

}
