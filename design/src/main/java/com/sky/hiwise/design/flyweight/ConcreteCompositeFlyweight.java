package com.sky.hiwise.design.flyweight;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConcreteCompositeFlyweight extends Flyweight {

	
	 private HashMap flyFiles = new HashMap(10);
	 private Flyweight flyweight;
	 
	 public ConcreteCompositeFlyweight(){
	 }
	 
	 public void add(Character key,Flyweight fly){
		 flyFiles.put(key, fly) ;
	 } 
	 /*
	  * 外蕴状态作为参数传入
	  * */
	@Override
	public void operation(String extrinsicState) {
		// TODO Auto-generated method stub
		Flyweight fly=null;
		for(Iterator it=flyFiles.entrySet().iterator();it.hasNext();){
			Map.Entry e=(Map.Entry)it.next();
			fly=(Flyweight)e.getValue();
			fly.operation(extrinsicState);
		}
	}

}
