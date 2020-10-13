package com.sky.hiwise.design.strategy;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product product1=new Product();
		product1.setName("手机");
		product1.setTotalPrice(900);
		Context ctx = new Context(new ConcreteStrategyA());
		int afterYouhui=ctx.contextMethod(product1.getTotalPrice());
		System.out.println(afterYouhui);
		/*ctx.setStrategy(new ConcreteStrategyB());
		ctx.contextMethod();*/
	}

}
