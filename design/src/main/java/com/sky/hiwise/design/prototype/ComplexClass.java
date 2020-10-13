package com.sky.hiwise.design.prototype;

import java.util.List;

public class ComplexClass implements Prototype {
	
	private String string;
	
	private List list;
	
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}


	public Prototype clone(){
		ComplexClass comp=new ComplexClass();
		comp.setList(this.getList());
		comp.setString(this.getString());
		return comp;
	}
	
	public String toString(){
		return this.getString()+"====== "+this.getList();
	}

}
