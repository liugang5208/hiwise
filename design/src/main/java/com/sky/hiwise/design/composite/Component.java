package com.sky.hiwise.design.composite;

abstract public class Component {
	
	protected String name;

    // Constructor
    public Component(String name)
    {
        this.name = name;
    }

    public abstract void Add(Component c);
    public abstract void Remove(Component c);
    public abstract void Display(int depth);


}
