package com.sky.hiwise.design.builder;

public class Director {

	 public void construct(Builder builder)
     {
         builder.buildPartA();
         builder.buildPartB();
     }

}
