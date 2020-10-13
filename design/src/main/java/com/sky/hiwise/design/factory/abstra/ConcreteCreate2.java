package com.sky.hiwise.design.factory.abstra;

public class ConcreteCreate2 implements Creator {

	@Override
	public ProductA factoryA() {
		// TODO Auto-generated method stub
		return new ConcretePorductA2();
	}

	@Override
	public ProductB factoryB() {
		// TODO Auto-generated method stub
		return new ConcretePorductB2();
	}

}
