package com.sky.hiwise.design.composite;

import java.util.ArrayList;

public class Composite extends Component {

	private ArrayList<Component> children = new ArrayList();
	 // Constructor
    public Composite(String name){  
    	super(name);
    }


	@Override
	public void Add(Component c) {
		// TODO Auto-generated method stub
		 children.add(c);
	}

	@Override
	public void Remove(Component c) {
		// TODO Auto-generated method stub
		children.remove(c);

	}

	@Override
	public void Display(int depth) {
		// TODO Auto-generated method stub
		System.out.println("===="+depth+name);
		for(Component c:children){
			c.Display(depth+2);
		}
	}

}
