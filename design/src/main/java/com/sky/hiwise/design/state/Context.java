package com.sky.hiwise.design.state;

public class Context {
	
	private State state;
	
	 public Context(State state)
     {
         this.state = state;
     }

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		System.out.println("State:"+state.getClass().getName());
	}

	 public void Request(){
		 state.Handle(this);
	 }

}
