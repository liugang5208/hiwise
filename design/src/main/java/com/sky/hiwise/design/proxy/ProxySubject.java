package com.sky.hiwise.design.proxy;

public class ProxySubject extends Subject {

	private RealSubject realSubject;
	@Override
	public void Request() {
		// TODO Auto-generated method stub
		preRequest();
		  if (realSubject == null)
          {
              realSubject = new RealSubject();
          }
          realSubject.Request();
          postRequest();
	}

	private void preRequest(){
		System.out.println("Before call Request!");
	}
	
	private void postRequest(){
		System.out.println("After call Request!");
	}
}
