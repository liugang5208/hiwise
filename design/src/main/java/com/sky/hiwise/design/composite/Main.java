package com.sky.hiwise.design.composite;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Composite root = new Composite("root");
          root.Add(new Leaf("Leaf A"));
          root.Add(new Leaf("Leaf B"));

          Composite comp = new Composite("Composite X");
          comp.Add(new Leaf("Leaf XA"));
          comp.Add(new Leaf("Leaf XB"));

          root.Add(comp);
          root.Add(new Leaf("Leaf C"));

          // Add and remove a leaf
          Leaf leaf = new Leaf("Leaf D");
          root.Add(leaf);
          root.Remove(leaf);

          // Recursively display tree
          root.Display(1);

	}

}
