package com.sky.hiwise.design.iterator;

public class ConcreteAggregate extends Aggregate {

	private Object[] objs={"hello","world"};
	@Override
	public Iterator CreateIterator() {
		// TODO Auto-generated method stub
		return new ConcreteIterator(this);
	}
	
	public Object getElement(int index){
		if(index<objs.length){
			return objs[index];
		}else{
			return null;
		}
	}
	
	public int size(){
		return objs.length;
	}

}
