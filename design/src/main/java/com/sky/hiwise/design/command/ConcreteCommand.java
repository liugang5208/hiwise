package com.sky.hiwise.design.command;

public class ConcreteCommand extends Command {

	private Receiver receiver;
	public ConcreteCommand(Receiver receiver){
		//super(receiver);
		this.receiver=receiver;
	}
	
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		receiver.action();
	}

}
