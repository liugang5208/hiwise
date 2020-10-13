package com.sky.hiwise.design.strategy;

public class Context {
	
	private Strategy strategy;
	
	public Context(Strategy strat){
		this.strategy=strat;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * 策略方法
	 */
	public int contextMethod(int total){
		return strategy.youhui(total);
	}

}
