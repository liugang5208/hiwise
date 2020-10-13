package com.sky.hiwise.design.strategy;

public class ConcreteStrategyB extends Strategy {

	@Override
	public int youhui(int total) {
		// TODO Auto-generated method stub
		System.out.println("策略B中可以写扩张方法，用以打折等操作！");
		return 0;
	}

}
