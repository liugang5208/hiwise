package com.sky.hiwise.design.command;

public class Invoker {

	private Command command;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void ExecuteCommand(){
		command.Execute();
	}
}
