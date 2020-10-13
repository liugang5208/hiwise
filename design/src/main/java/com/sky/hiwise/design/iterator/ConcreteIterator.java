package com.sky.hiwise.design.iterator;

public class ConcreteIterator implements Iterator {

	private ConcreteAggregate conAggre;
	private int index=0;
	private int size=0;
	
	public ConcreteIterator(ConcreteAggregate conAggre){
		this.conAggre=conAggre;
		this.size=conAggre.size();
		index=0;
	}
	
	@Override
	public void First() {
		// TODO Auto-generated method stub
		index=0;

	}

	@Override
	public void Next() {
		// TODO Auto-generated method stub
		if(index<size){
			index++;
		}

	}

	@Override
	public boolean IsDone() {
		// TODO Auto-generated method stub
		return (index>=size);
	}

	@Override
	public Object CurrentItem() {
		// TODO Auto-generated method stub
		return conAggre.getElement(index);
	}

}
