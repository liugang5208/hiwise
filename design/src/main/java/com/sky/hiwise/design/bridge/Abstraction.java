package com.sky.hiwise.design.bridge;

abstract public class Abstraction {
	protected Implementor impl;
	
	public void operation(){
		impl.operationImpl();
	}
 
}
