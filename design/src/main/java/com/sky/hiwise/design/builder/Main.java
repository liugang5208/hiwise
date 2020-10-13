package com.sky.hiwise.design.builder;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 Director director = new Director();
		 Builder b1 = new ConcreteBuilder1();
         Builder b2 = new ConcreteBuilder2();
         director.construct(b1);
         Product prod1=b1.getProduct();
         prod1.Show();
         director.construct(b2);
         Product prod2=b2.getProduct();
         prod2.Show();

	}

}
