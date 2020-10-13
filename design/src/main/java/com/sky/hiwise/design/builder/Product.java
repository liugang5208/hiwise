package com.sky.hiwise.design.builder;

import java.util.ArrayList;

public class Product {
	
	 ArrayList<String> parts = new ArrayList();
	  
     public void Add(String part)
     {
         parts.add(part);
     }

     public void Show()
     {
         System.out.println("\nProduct Parts -------");
        for(String part: parts){
           System.out.println(part);    
        }
 }


}
