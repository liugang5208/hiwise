package com.sky.hiwise.design.memento;

public class Client {
	
	private static Originator origin=new Originator();
	private static Caretaker caretaker=new Caretaker();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		origin.setState("On");
		caretaker.saveMemento(origin.CreateMemento());
		origin.setState("Off");
		origin.restoreMemento(caretaker.retrieveMemento());

	}

}
