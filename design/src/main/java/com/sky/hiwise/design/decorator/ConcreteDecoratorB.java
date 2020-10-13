package com.sky.hiwise.design.decorator;

public class ConcreteDecoratorB extends Decorator{
	
	   public void Operation()
       {
		   component.Operation();
           AddedBehavior();
           System.out.println("ConcreteDecoratorB.Operation()");
       }

       public void AddedBehavior()
       {
       }

}
