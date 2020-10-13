package com.sky.hiwise.design.mediator;

public class ConcreteColleague2 extends Colleague {

	public ConcreteColleague2(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}

	public void Send(String message)
    {
		 mediator.colleagueChanged(message, this);
    }
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("This is an action from  ConcreteColleague2");
		

	}

}
