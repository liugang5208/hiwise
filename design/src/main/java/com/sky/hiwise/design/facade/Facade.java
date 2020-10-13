package com.sky.hiwise.design.facade;

public class Facade {

	
	private SubSystemOne one;
	private SubSystemTwo two;
	private SubSystemThree three;
	private SubSystemFour four;

    public Facade()
    {
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
        four = new SubSystemFour();
    }
    
    public void MethodA()
    {
        System.out.println("\nMethodA() ---- ");
        one.MethodOne();
        two.MethodTwo();
        four.MethodFour();
    }

    public void MethodB()
    {
        System.out.println("\nMethodB() ---- ");
        two.MethodTwo();
        three.MethodThree();
    }


}
