package com.sky.hiwise.design.state;

public class ConcreteStateA extends State {

	@Override
	public void Handle(Context context) {
		// TODO Auto-generated method stub
		context.setState(new ConcreteStateB());
	}

}
