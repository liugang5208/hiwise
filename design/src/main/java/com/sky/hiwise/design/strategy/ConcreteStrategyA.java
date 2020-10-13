package com.sky.hiwise.design.strategy;

public class ConcreteStrategyA extends Strategy {

	@Override
	public int youhui(int total) {
		// TODO Auto-generated method stub
		if(total<200){
			return total;
		}else{
		return total=total-(total/200)*20;
		}

	}

}
