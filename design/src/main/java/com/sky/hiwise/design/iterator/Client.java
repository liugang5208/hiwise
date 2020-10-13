package com.sky.hiwise.design.iterator;

public class Client {

	/**
	 * @param args
	 * 
	 */
	private Iterator iter;
	private Aggregate agg=new ConcreteAggregate();
	
	public void operation(){
		iter=agg.CreateIterator();
		while(!iter.IsDone()){
			System.out.println(iter.CurrentItem().toString());
			iter.Next();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Client client=new Client();
		client.operation();
	}

}
