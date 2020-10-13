package com.sky.hiwise.design.adapter;

public class AdapterObj implements Target {
	
	private Adaptee adaptee;
	 
	public Adaptee getAdaptee() {
		return adaptee;
	}

	public void setAdaptee(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void sampleOperation1() {
		// TODO Auto-generated method stub
		System.out.println("-----------");
		adaptee.sampleOperation1();
		

	}

	@Override
	public void sampleOperation2() {
		// TODO Auto-generated method stub
		System.out.println("This sampleOperation2 in the AdapterObj!");

	}

}
