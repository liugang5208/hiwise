package com.sky.hiwise.design.observer;

import java.util.ArrayList;
import java.util.List;

abstract class Subject {
	
	 private List<Observer> observers = new ArrayList<Observer>();

     public void Attach(Observer observer)
     {
         observers.add(observer);
     }

     public void Detach(Observer observer)
     {
         observers.remove(observer);
     }

     public void Notify()
     {
        for(Observer o: observers)
         {
             o.Update();
         }
     }


}
