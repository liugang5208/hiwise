package com.sky.hiwise.design.observer;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Configure Observer pattern
        ConcreteSubject s = new ConcreteSubject();

        s.Attach(new ConcreteObserver(s,"X"));
        s.Attach(new ConcreteObserver(s,"Y"));
        s.Attach(new ConcreteObserver(s,"Z"));

        // Change subject and notify observers
        s.setSubjectState ("ABC");
        s.Notify();
	}

}
