package com.sky.hiwise.design.command;

public class Client {

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
        Command  command  = new ConcreteCommand(receiver);
        Invoker  invoker  = new Invoker();

        // Set and execute command
        invoker.setCommand(command);
        invoker.ExecuteCommand();


	}
}
