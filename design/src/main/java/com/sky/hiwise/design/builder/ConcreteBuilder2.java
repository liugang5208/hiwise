package com.sky.hiwise.design.builder;

public class ConcreteBuilder2 extends Builder {

	private Product product = new Product();
	
	@Override
	public void buildPartA() {
		product.Add("创建PartX");

	}

	@Override
	public void buildPartB() {
		product.Add("创建PartY");

	}
	
	@Override
	public Product getProduct() {
		return product;
 
	}

}
