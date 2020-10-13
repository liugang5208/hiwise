package com.sky.hiwise.design.builder;

public class ConcreteBuilder1  extends Builder {
	
	private Product product = new Product();
	
	@Override
	public void buildPartA() {
		product.Add("创建PartA");

	}

	@Override
	public void buildPartB() {
		product.Add("创建PartB");

	}

	@Override
	public Product getProduct() {
        return product;
	}
}
