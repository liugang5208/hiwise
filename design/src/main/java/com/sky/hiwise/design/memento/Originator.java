package com.sky.hiwise.design.memento;

public class Originator {

	private String state;
	
	 public Memento CreateMemento()
     {
         return (new Memento(state));
     }

	 public void restoreMemento(Memento memento){
		 this.state=memento.getState();
	 }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		System.out.println("Current State :"+this.state);
	}
	
	
}
