package com.sky.hiwise.design.state;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Context cont = new Context(new ConcreteStateA());
		 cont.Request();
		 cont.Request();
		 cont.Request();
		 cont.Request();
	}

}
