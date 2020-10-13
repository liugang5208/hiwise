package com.sky.hiwise.design.visitor;

public class VisitorA implements Visitor {

	@Override
	public void visit(NodeA nodeA) {
		// TODO Auto-generated method stub
		System.out.println(nodeA.operationA());

	}

	@Override
	public void visit(NodeB nodeB) {
		// TODO Auto-generated method stub
		System.out.println(nodeB.operationB());

	}

}
