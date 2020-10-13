package com.sky.hiwise.design.mediator;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 ConcreteMediator m = new ConcreteMediator();

         ConcreteColleague1 c1 = new ConcreteColleague1(m);
         ConcreteColleague2 c2 = new ConcreteColleague2(m);

         m.setColleague1(c1);
         m.setColleague2(c2);

         c1.Send("How are you?");
         c2.Send("Fine, thanks");


	}

}
