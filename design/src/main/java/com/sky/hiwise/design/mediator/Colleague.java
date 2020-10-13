package com.sky.hiwise.design.mediator;

public abstract class Colleague {
	
	 protected Mediator mediator;
	 
	 public Colleague(Mediator mediator){
		 this.mediator=mediator;
	 }

	public Mediator getMediator() {
		return mediator;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}
	 
	 public abstract void action();
	 
	 public void changed(String message){
		 mediator.colleagueChanged(message, this);
	 }

}
