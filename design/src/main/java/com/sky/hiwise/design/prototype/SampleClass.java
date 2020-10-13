package com.sky.hiwise.design.prototype;

public class SampleClass implements Prototype {
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public SampleClass(int count){
		super();
		this.count=count;
	}
	public Prototype clone(){
		return new SampleClass(this.getCount());
	}
	
	public String toString(){
		return Integer.toString(this.getCount());
	}
}
