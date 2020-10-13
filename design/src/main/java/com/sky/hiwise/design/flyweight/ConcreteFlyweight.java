package com.sky.hiwise.design.flyweight;

public class ConcreteFlyweight extends Flyweight {

	private Character intrinsicState=null;
	
	public ConcreteFlyweight(Character state){
		this.intrinsicState=state;
	}
	@Override
	public void operation(String state) {
		// TODO Auto-generated method stub
		System.out.println("\n intrinsicState="+intrinsicState+"\n ExtrinsicState="+state);
	}

}
