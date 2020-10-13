package com.sky.hiwise.design.flyweight;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FlyweightFactory {
	
	  private HashMap flyweights = new HashMap();
	  private Flyweight lnkFlyweight;

      // Constructor
      public FlyweightFactory(){
      }

      public Flyweight GetFlyweight(Character state){
    	  if(flyweights.containsKey(state)){
    		  return (Flyweight)flyweights.get(state);
    	  }else{
    		  Flyweight fly=new ConcreteFlyweight(state);
    		  flyweights.put(state, fly);
    		  return fly;
    	  }
      }
      
      public void checkFlyweight(){
    	  Flyweight fly;
    	  int i=0;
    	  System.out.println("\n =====checkFlyweight=====");
    	  for(Iterator it=flyweights.entrySet().iterator();it.hasNext();){
    		 Map.Entry e=(Map.Entry)it.next();
    		 System.out.println("Item " +(++i)+":"+e.getKey());
    	  }
    	  System.out.println("=====checkFlyweight=====");
      }


}
