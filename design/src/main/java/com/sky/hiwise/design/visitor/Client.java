package com.sky.hiwise.design.visitor;

//import com.google.common.base.Objects;

public class Client {
	
	private static ObjectStructure objstruct;
	private static Visitor visitor;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		objstruct=new ObjectStructure();
		
		objstruct.add(new NodeA());
		
		objstruct.add(new NodeB());
		visitor=new VisitorA();
		objstruct.action(visitor);
		
		visitor=new VisitorB();
		objstruct.action(visitor);
	}

}
