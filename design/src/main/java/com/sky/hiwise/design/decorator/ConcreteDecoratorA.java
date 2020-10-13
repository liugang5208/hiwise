package com.sky.hiwise.design.decorator;

public class ConcreteDecoratorA extends Decorator {
	
	 private String addedState;

     public void Operation()
     {
    	 component.Operation();
         addedState = "New State";
        System.out.println("ConcreteDecoratorA.Operation()");
     }


}
