package com.sky.hiwise.design.adapter;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Adapter adapter=new Adapter();
		adapter.sampleOperation1();
		adapter.sampleOperation2();
		AdapterObj adapterObj=new AdapterObj();
		Adaptee adaptee=new Adaptee();
		adapterObj.setAdaptee(adaptee);
		adapterObj.sampleOperation1();
		adapterObj.sampleOperation2();
		

	}

}
