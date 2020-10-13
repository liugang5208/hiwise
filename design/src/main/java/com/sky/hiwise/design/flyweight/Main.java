package com.sky.hiwise.design.flyweight;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FlyweightFactory flyFactory=new FlyweightFactory();
		Flyweight fly=flyFactory.GetFlyweight(new Character('a'));
		fly.operation("First Call");
		fly=flyFactory.GetFlyweight(new Character('b'));
		fly.operation("Second Call");
		fly=flyFactory.GetFlyweight(new Character('a'));
		fly.operation("Third Call");
		flyFactory.checkFlyweight();
		CompositeFlyweightFactory compositeFactory=new CompositeFlyweightFactory();
		
		Flyweight compositeFly=compositeFactory.GetFlyweight("aba");
		compositeFly.operation("Composite Call");
		compositeFactory.checkFlyweight();
		
	}

}
