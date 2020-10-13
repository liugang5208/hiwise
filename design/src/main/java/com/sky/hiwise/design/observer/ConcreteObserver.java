package com.sky.hiwise.design.observer;

public class ConcreteObserver extends Observer {
	
	 private String name;
     private String observerState;
     private ConcreteSubject subject;

     public ConcreteObserver(ConcreteSubject subject, String name){
    	 this.subject = subject;
         this.name = name;
     }
     
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		observerState = subject.getSubjectState();
        System.out.println("Observer"+name+"'s new state is "+observerState);
	}

	public ConcreteSubject getSubject() {
		return subject;
	}

	public void setSubject(ConcreteSubject subject) {
		this.subject = subject;
	}

	
}
